import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';
import { FinancePartnerService } from 'app/entities/finance-partner/service/finance-partner.service';
import { IAcceptedOffer } from '../accepted-offer.model';
import { AcceptedOfferService } from '../service/accepted-offer.service';
import { AcceptedOfferFormService } from './accepted-offer-form.service';

import { AcceptedOfferUpdateComponent } from './accepted-offer-update.component';

describe('AcceptedOffer Management Update Component', () => {
  let comp: AcceptedOfferUpdateComponent;
  let fixture: ComponentFixture<AcceptedOfferUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let acceptedOfferFormService: AcceptedOfferFormService;
  let acceptedOfferService: AcceptedOfferService;
  let financeRequestService: FinanceRequestService;
  let anchorTraderService: AnchorTraderService;
  let financePartnerService: FinancePartnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AcceptedOfferUpdateComponent],
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
      .overrideTemplate(AcceptedOfferUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AcceptedOfferUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    acceptedOfferFormService = TestBed.inject(AcceptedOfferFormService);
    acceptedOfferService = TestBed.inject(AcceptedOfferService);
    financeRequestService = TestBed.inject(FinanceRequestService);
    anchorTraderService = TestBed.inject(AnchorTraderService);
    financePartnerService = TestBed.inject(FinancePartnerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const acceptedOffer: IAcceptedOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 12251 };
      acceptedOffer.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 17158 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ acceptedOffer });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call AnchorTrader query and add missing value', () => {
      const acceptedOffer: IAcceptedOffer = { id: 456 };
      const anchortrader: IAnchorTrader = { id: 11920 };
      acceptedOffer.anchortrader = anchortrader;

      const anchorTraderCollection: IAnchorTrader[] = [{ id: 8905 }];
      jest.spyOn(anchorTraderService, 'query').mockReturnValue(of(new HttpResponse({ body: anchorTraderCollection })));
      const additionalAnchorTraders = [anchortrader];
      const expectedCollection: IAnchorTrader[] = [...additionalAnchorTraders, ...anchorTraderCollection];
      jest.spyOn(anchorTraderService, 'addAnchorTraderToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ acceptedOffer });
      comp.ngOnInit();

      expect(anchorTraderService.query).toHaveBeenCalled();
      expect(anchorTraderService.addAnchorTraderToCollectionIfMissing).toHaveBeenCalledWith(
        anchorTraderCollection,
        ...additionalAnchorTraders.map(expect.objectContaining),
      );
      expect(comp.anchorTradersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call FinancePartner query and add missing value', () => {
      const acceptedOffer: IAcceptedOffer = { id: 456 };
      const financepartner: IFinancePartner = { id: 20779 };
      acceptedOffer.financepartner = financepartner;

      const financePartnerCollection: IFinancePartner[] = [{ id: 16903 }];
      jest.spyOn(financePartnerService, 'query').mockReturnValue(of(new HttpResponse({ body: financePartnerCollection })));
      const additionalFinancePartners = [financepartner];
      const expectedCollection: IFinancePartner[] = [...additionalFinancePartners, ...financePartnerCollection];
      jest.spyOn(financePartnerService, 'addFinancePartnerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ acceptedOffer });
      comp.ngOnInit();

      expect(financePartnerService.query).toHaveBeenCalled();
      expect(financePartnerService.addFinancePartnerToCollectionIfMissing).toHaveBeenCalledWith(
        financePartnerCollection,
        ...additionalFinancePartners.map(expect.objectContaining),
      );
      expect(comp.financePartnersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const acceptedOffer: IAcceptedOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 10625 };
      acceptedOffer.financerequest = financerequest;
      const anchortrader: IAnchorTrader = { id: 194 };
      acceptedOffer.anchortrader = anchortrader;
      const financepartner: IFinancePartner = { id: 26221 };
      acceptedOffer.financepartner = financepartner;

      activatedRoute.data = of({ acceptedOffer });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.anchorTradersSharedCollection).toContain(anchortrader);
      expect(comp.financePartnersSharedCollection).toContain(financepartner);
      expect(comp.acceptedOffer).toEqual(acceptedOffer);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAcceptedOffer>>();
      const acceptedOffer = { id: 123 };
      jest.spyOn(acceptedOfferFormService, 'getAcceptedOffer').mockReturnValue(acceptedOffer);
      jest.spyOn(acceptedOfferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acceptedOffer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: acceptedOffer }));
      saveSubject.complete();

      // THEN
      expect(acceptedOfferFormService.getAcceptedOffer).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(acceptedOfferService.update).toHaveBeenCalledWith(expect.objectContaining(acceptedOffer));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAcceptedOffer>>();
      const acceptedOffer = { id: 123 };
      jest.spyOn(acceptedOfferFormService, 'getAcceptedOffer').mockReturnValue({ id: null });
      jest.spyOn(acceptedOfferService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acceptedOffer: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: acceptedOffer }));
      saveSubject.complete();

      // THEN
      expect(acceptedOfferFormService.getAcceptedOffer).toHaveBeenCalled();
      expect(acceptedOfferService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAcceptedOffer>>();
      const acceptedOffer = { id: 123 };
      jest.spyOn(acceptedOfferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acceptedOffer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(acceptedOfferService.update).toHaveBeenCalled();
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

    describe('compareAnchorTrader', () => {
      it('Should forward to anchorTraderService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(anchorTraderService, 'compareAnchorTrader');
        comp.compareAnchorTrader(entity, entity2);
        expect(anchorTraderService.compareAnchorTrader).toHaveBeenCalledWith(entity, entity2);
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
