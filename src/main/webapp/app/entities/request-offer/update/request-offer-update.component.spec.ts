import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { CBCREProcessService } from 'app/entities/cbcre-process/service/cbcre-process.service';
import { IRequestOffer } from '../request-offer.model';
import { RequestOfferService } from '../service/request-offer.service';
import { RequestOfferFormService } from './request-offer-form.service';

import { RequestOfferUpdateComponent } from './request-offer-update.component';

describe('RequestOffer Management Update Component', () => {
  let comp: RequestOfferUpdateComponent;
  let fixture: ComponentFixture<RequestOfferUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let requestOfferFormService: RequestOfferFormService;
  let requestOfferService: RequestOfferService;
  let financeRequestService: FinanceRequestService;
  let cBCREProcessService: CBCREProcessService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RequestOfferUpdateComponent],
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
      .overrideTemplate(RequestOfferUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RequestOfferUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    requestOfferFormService = TestBed.inject(RequestOfferFormService);
    requestOfferService = TestBed.inject(RequestOfferService);
    financeRequestService = TestBed.inject(FinanceRequestService);
    cBCREProcessService = TestBed.inject(CBCREProcessService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const requestOffer: IRequestOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 6108 };
      requestOffer.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 21530 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ requestOffer });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call CBCREProcess query and add missing value', () => {
      const requestOffer: IRequestOffer = { id: 456 };
      const cbcreprocess: ICBCREProcess = { id: 6242 };
      requestOffer.cbcreprocess = cbcreprocess;

      const cBCREProcessCollection: ICBCREProcess[] = [{ id: 83 }];
      jest.spyOn(cBCREProcessService, 'query').mockReturnValue(of(new HttpResponse({ body: cBCREProcessCollection })));
      const additionalCBCREProcesses = [cbcreprocess];
      const expectedCollection: ICBCREProcess[] = [...additionalCBCREProcesses, ...cBCREProcessCollection];
      jest.spyOn(cBCREProcessService, 'addCBCREProcessToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ requestOffer });
      comp.ngOnInit();

      expect(cBCREProcessService.query).toHaveBeenCalled();
      expect(cBCREProcessService.addCBCREProcessToCollectionIfMissing).toHaveBeenCalledWith(
        cBCREProcessCollection,
        ...additionalCBCREProcesses.map(expect.objectContaining),
      );
      expect(comp.cBCREProcessesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const requestOffer: IRequestOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 26632 };
      requestOffer.financerequest = financerequest;
      const cbcreprocess: ICBCREProcess = { id: 16180 };
      requestOffer.cbcreprocess = cbcreprocess;

      activatedRoute.data = of({ requestOffer });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.cBCREProcessesSharedCollection).toContain(cbcreprocess);
      expect(comp.requestOffer).toEqual(requestOffer);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRequestOffer>>();
      const requestOffer = { id: 123 };
      jest.spyOn(requestOfferFormService, 'getRequestOffer').mockReturnValue(requestOffer);
      jest.spyOn(requestOfferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ requestOffer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: requestOffer }));
      saveSubject.complete();

      // THEN
      expect(requestOfferFormService.getRequestOffer).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(requestOfferService.update).toHaveBeenCalledWith(expect.objectContaining(requestOffer));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRequestOffer>>();
      const requestOffer = { id: 123 };
      jest.spyOn(requestOfferFormService, 'getRequestOffer').mockReturnValue({ id: null });
      jest.spyOn(requestOfferService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ requestOffer: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: requestOffer }));
      saveSubject.complete();

      // THEN
      expect(requestOfferFormService.getRequestOffer).toHaveBeenCalled();
      expect(requestOfferService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRequestOffer>>();
      const requestOffer = { id: 123 };
      jest.spyOn(requestOfferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ requestOffer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(requestOfferService.update).toHaveBeenCalled();
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

    describe('compareCBCREProcess', () => {
      it('Should forward to cBCREProcessService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(cBCREProcessService, 'compareCBCREProcess');
        comp.compareCBCREProcess(entity, entity2);
        expect(cBCREProcessService.compareCBCREProcess).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
