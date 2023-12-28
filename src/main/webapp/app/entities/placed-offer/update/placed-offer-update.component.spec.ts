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
import { IPlacedOffer } from '../placed-offer.model';
import { PlacedOfferService } from '../service/placed-offer.service';
import { PlacedOfferFormService } from './placed-offer-form.service';

import { PlacedOfferUpdateComponent } from './placed-offer-update.component';

describe('PlacedOffer Management Update Component', () => {
  let comp: PlacedOfferUpdateComponent;
  let fixture: ComponentFixture<PlacedOfferUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let placedOfferFormService: PlacedOfferFormService;
  let placedOfferService: PlacedOfferService;
  let financeRequestService: FinanceRequestService;
  let financePartnerService: FinancePartnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), PlacedOfferUpdateComponent],
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
      .overrideTemplate(PlacedOfferUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PlacedOfferUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    placedOfferFormService = TestBed.inject(PlacedOfferFormService);
    placedOfferService = TestBed.inject(PlacedOfferService);
    financeRequestService = TestBed.inject(FinanceRequestService);
    financePartnerService = TestBed.inject(FinancePartnerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const placedOffer: IPlacedOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 7440 };
      placedOffer.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 2247 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ placedOffer });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call FinancePartner query and add missing value', () => {
      const placedOffer: IPlacedOffer = { id: 456 };
      const financepartner: IFinancePartner = { id: 8437 };
      placedOffer.financepartner = financepartner;

      const financePartnerCollection: IFinancePartner[] = [{ id: 24106 }];
      jest.spyOn(financePartnerService, 'query').mockReturnValue(of(new HttpResponse({ body: financePartnerCollection })));
      const additionalFinancePartners = [financepartner];
      const expectedCollection: IFinancePartner[] = [...additionalFinancePartners, ...financePartnerCollection];
      jest.spyOn(financePartnerService, 'addFinancePartnerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ placedOffer });
      comp.ngOnInit();

      expect(financePartnerService.query).toHaveBeenCalled();
      expect(financePartnerService.addFinancePartnerToCollectionIfMissing).toHaveBeenCalledWith(
        financePartnerCollection,
        ...additionalFinancePartners.map(expect.objectContaining),
      );
      expect(comp.financePartnersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const placedOffer: IPlacedOffer = { id: 456 };
      const financerequest: IFinanceRequest = { id: 25531 };
      placedOffer.financerequest = financerequest;
      const financepartner: IFinancePartner = { id: 30738 };
      placedOffer.financepartner = financepartner;

      activatedRoute.data = of({ placedOffer });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.financePartnersSharedCollection).toContain(financepartner);
      expect(comp.placedOffer).toEqual(placedOffer);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPlacedOffer>>();
      const placedOffer = { id: 123 };
      jest.spyOn(placedOfferFormService, 'getPlacedOffer').mockReturnValue(placedOffer);
      jest.spyOn(placedOfferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ placedOffer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: placedOffer }));
      saveSubject.complete();

      // THEN
      expect(placedOfferFormService.getPlacedOffer).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(placedOfferService.update).toHaveBeenCalledWith(expect.objectContaining(placedOffer));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPlacedOffer>>();
      const placedOffer = { id: 123 };
      jest.spyOn(placedOfferFormService, 'getPlacedOffer').mockReturnValue({ id: null });
      jest.spyOn(placedOfferService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ placedOffer: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: placedOffer }));
      saveSubject.complete();

      // THEN
      expect(placedOfferFormService.getPlacedOffer).toHaveBeenCalled();
      expect(placedOfferService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPlacedOffer>>();
      const placedOffer = { id: 123 };
      jest.spyOn(placedOfferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ placedOffer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(placedOfferService.update).toHaveBeenCalled();
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
