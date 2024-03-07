import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { CBCREProcessService } from '../service/cbcre-process.service';
import { ICBCREProcess } from '../cbcre-process.model';
import { CBCREProcessFormService } from './cbcre-process-form.service';

import { CBCREProcessUpdateComponent } from './cbcre-process-update.component';

describe('CBCREProcess Management Update Component', () => {
  let comp: CBCREProcessUpdateComponent;
  let fixture: ComponentFixture<CBCREProcessUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cBCREProcessFormService: CBCREProcessFormService;
  let cBCREProcessService: CBCREProcessService;
  let financeRequestService: FinanceRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CBCREProcessUpdateComponent],
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
      .overrideTemplate(CBCREProcessUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CBCREProcessUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cBCREProcessFormService = TestBed.inject(CBCREProcessFormService);
    cBCREProcessService = TestBed.inject(CBCREProcessService);
    financeRequestService = TestBed.inject(FinanceRequestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const cBCREProcess: ICBCREProcess = { id: 456 };
      const financeRequest: IFinanceRequest = { id: 7138 };
      cBCREProcess.financeRequest = financeRequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 31365 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financeRequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cBCREProcess });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const cBCREProcess: ICBCREProcess = { id: 456 };
      const financeRequest: IFinanceRequest = { id: 5983 };
      cBCREProcess.financeRequest = financeRequest;

      activatedRoute.data = of({ cBCREProcess });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financeRequest);
      expect(comp.cBCREProcess).toEqual(cBCREProcess);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICBCREProcess>>();
      const cBCREProcess = { id: 123 };
      jest.spyOn(cBCREProcessFormService, 'getCBCREProcess').mockReturnValue(cBCREProcess);
      jest.spyOn(cBCREProcessService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cBCREProcess });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cBCREProcess }));
      saveSubject.complete();

      // THEN
      expect(cBCREProcessFormService.getCBCREProcess).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cBCREProcessService.update).toHaveBeenCalledWith(expect.objectContaining(cBCREProcess));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICBCREProcess>>();
      const cBCREProcess = { id: 123 };
      jest.spyOn(cBCREProcessFormService, 'getCBCREProcess').mockReturnValue({ id: null });
      jest.spyOn(cBCREProcessService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cBCREProcess: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cBCREProcess }));
      saveSubject.complete();

      // THEN
      expect(cBCREProcessFormService.getCBCREProcess).toHaveBeenCalled();
      expect(cBCREProcessService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICBCREProcess>>();
      const cBCREProcess = { id: 123 };
      jest.spyOn(cBCREProcessService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cBCREProcess });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cBCREProcessService.update).toHaveBeenCalled();
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
