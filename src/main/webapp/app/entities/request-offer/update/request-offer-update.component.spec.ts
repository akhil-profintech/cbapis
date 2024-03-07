import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';
import { FinancePartnerService } from 'app/entities/finance-partner/service/finance-partner.service';
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
  let financePartnerService: FinancePartnerService;

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
    financePartnerService = TestBed.inject(FinancePartnerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const requestOffer: IRequestOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 9167 };
      requestOffer.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 452 }];
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

    it('Should call FinancePartner query and add missing value', () => {
      const requestOffer: IRequestOffer = { id: 456 };
      const financepartner: IFinancePartner = { id: 30861 };
      requestOffer.financepartner = financepartner;

      const financePartnerCollection: IFinancePartner[] = [{ id: 6276 }];
      jest.spyOn(financePartnerService, 'query').mockReturnValue(of(new HttpResponse({ body: financePartnerCollection })));
      const additionalFinancePartners = [financepartner];
      const expectedCollection: IFinancePartner[] = [...additionalFinancePartners, ...financePartnerCollection];
      jest.spyOn(financePartnerService, 'addFinancePartnerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ requestOffer });
      comp.ngOnInit();

      expect(financePartnerService.query).toHaveBeenCalled();
      expect(financePartnerService.addFinancePartnerToCollectionIfMissing).toHaveBeenCalledWith(
        financePartnerCollection,
        ...additionalFinancePartners.map(expect.objectContaining),
      );
      expect(comp.financePartnersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const requestOffer: IRequestOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 13745 };
      requestOffer.financerequest = financerequest;
      const financepartner: IFinancePartner = { id: 26757 };
      requestOffer.financepartner = financepartner;

      activatedRoute.data = of({ requestOffer });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.financePartnersSharedCollection).toContain(financepartner);
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

    describe('compareFinancePartner', () => {
      it('Should forward to financePartnerService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(financePartnerService, 'compareFinancePartner');
        comp.compareFinancePartner(entity, entity2);
        expect(financePartnerService.compareFinancePartner).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
