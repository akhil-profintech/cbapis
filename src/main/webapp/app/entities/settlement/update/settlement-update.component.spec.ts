import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { SettlementService } from '../service/settlement.service';
import { ISettlement } from '../settlement.model';
import { SettlementFormService } from './settlement-form.service';

import { SettlementUpdateComponent } from './settlement-update.component';

describe('Settlement Management Update Component', () => {
  let comp: SettlementUpdateComponent;
  let fixture: ComponentFixture<SettlementUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let settlementFormService: SettlementFormService;
  let settlementService: SettlementService;
  let financeRequestService: FinanceRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), SettlementUpdateComponent],
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
      .overrideTemplate(SettlementUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SettlementUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    settlementFormService = TestBed.inject(SettlementFormService);
    settlementService = TestBed.inject(SettlementService);
    financeRequestService = TestBed.inject(FinanceRequestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const settlement: ISettlement = { id: 456 };
      const financerequest: IFinanceRequest = { id: 12633 };
      settlement.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 13706 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ settlement });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const settlement: ISettlement = { id: 456 };
      const financerequest: IFinanceRequest = { id: 11303 };
      settlement.financerequest = financerequest;

      activatedRoute.data = of({ settlement });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.settlement).toEqual(settlement);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISettlement>>();
      const settlement = { id: 123 };
      jest.spyOn(settlementFormService, 'getSettlement').mockReturnValue(settlement);
      jest.spyOn(settlementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ settlement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: settlement }));
      saveSubject.complete();

      // THEN
      expect(settlementFormService.getSettlement).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(settlementService.update).toHaveBeenCalledWith(expect.objectContaining(settlement));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISettlement>>();
      const settlement = { id: 123 };
      jest.spyOn(settlementFormService, 'getSettlement').mockReturnValue({ id: null });
      jest.spyOn(settlementService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ settlement: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: settlement }));
      saveSubject.complete();

      // THEN
      expect(settlementFormService.getSettlement).toHaveBeenCalled();
      expect(settlementService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISettlement>>();
      const settlement = { id: 123 };
      jest.spyOn(settlementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ settlement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(settlementService.update).toHaveBeenCalled();
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
