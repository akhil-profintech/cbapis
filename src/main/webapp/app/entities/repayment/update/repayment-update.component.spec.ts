import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { RepaymentService } from '../service/repayment.service';
import { IRepayment } from '../repayment.model';
import { RepaymentFormService } from './repayment-form.service';

import { RepaymentUpdateComponent } from './repayment-update.component';

describe('Repayment Management Update Component', () => {
  let comp: RepaymentUpdateComponent;
  let fixture: ComponentFixture<RepaymentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let repaymentFormService: RepaymentFormService;
  let repaymentService: RepaymentService;
  let financeRequestService: FinanceRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RepaymentUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(RepaymentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RepaymentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    repaymentFormService = TestBed.inject(RepaymentFormService);
    repaymentService = TestBed.inject(RepaymentService);
    financeRequestService = TestBed.inject(FinanceRequestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const repayment: IRepayment = { id: 456 };
      const financerequest: IFinanceRequest = { id: 11311 };
      repayment.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 15730 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ repayment });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const repayment: IRepayment = { id: 456 };
      const financerequest: IFinanceRequest = { id: 19989 };
      repayment.financerequest = financerequest;

      activatedRoute.data = of({ repayment });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.repayment).toEqual(repayment);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRepayment>>();
      const repayment = { id: 123 };
      jest.spyOn(repaymentFormService, 'getRepayment').mockReturnValue(repayment);
      jest.spyOn(repaymentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ repayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: repayment }));
      saveSubject.complete();

      // THEN
      expect(repaymentFormService.getRepayment).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(repaymentService.update).toHaveBeenCalledWith(expect.objectContaining(repayment));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRepayment>>();
      const repayment = { id: 123 };
      jest.spyOn(repaymentFormService, 'getRepayment').mockReturnValue({ id: null });
      jest.spyOn(repaymentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ repayment: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: repayment }));
      saveSubject.complete();

      // THEN
      expect(repaymentFormService.getRepayment).toHaveBeenCalled();
      expect(repaymentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRepayment>>();
      const repayment = { id: 123 };
      jest.spyOn(repaymentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ repayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(repaymentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareFinanceRequest', () => {
      it('Should forward to financeRequestService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(financeRequestService, 'compareFinanceRequest');
        comp.compareFinanceRequest(entity, entity2);
        expect(financeRequestService.compareFinanceRequest).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
