import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../placed-offer.test-samples';

import { PlacedOfferFormService } from './placed-offer-form.service';

describe('PlacedOffer Form Service', () => {
  let service: PlacedOfferFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlacedOfferFormService);
  });

  describe('Service methods', () => {
    describe('createPlacedOfferFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPlacedOfferFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reqOffId: expect.any(Object),
            placedOfferId: expect.any(Object),
            placedOfferRefNo: expect.any(Object),
            requestOfferRefNo: expect.any(Object),
            value: expect.any(Object),
            reqAmount: expect.any(Object),
            marginPtg: expect.any(Object),
            marginValue: expect.any(Object),
            amountAftMargin: expect.any(Object),
            interestPtg: expect.any(Object),
            term: expect.any(Object),
            interestValue: expect.any(Object),
            netAmount: expect.any(Object),
            offerDate: expect.any(Object),
            requestId: expect.any(Object),
            placedOfferDate: expect.any(Object),
            anchorTrader: expect.any(Object),
            tradePartner: expect.any(Object),
            disbursalAmount: expect.any(Object),
            status: expect.any(Object),
            financerequest: expect.any(Object),
            financepartner: expect.any(Object),
          }),
        );
      });

      it('passing IPlacedOffer should create a new form with FormGroup', () => {
        const formGroup = service.createPlacedOfferFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reqOffId: expect.any(Object),
            placedOfferId: expect.any(Object),
            placedOfferRefNo: expect.any(Object),
            requestOfferRefNo: expect.any(Object),
            value: expect.any(Object),
            reqAmount: expect.any(Object),
            marginPtg: expect.any(Object),
            marginValue: expect.any(Object),
            amountAftMargin: expect.any(Object),
            interestPtg: expect.any(Object),
            term: expect.any(Object),
            interestValue: expect.any(Object),
            netAmount: expect.any(Object),
            offerDate: expect.any(Object),
            requestId: expect.any(Object),
            placedOfferDate: expect.any(Object),
            anchorTrader: expect.any(Object),
            tradePartner: expect.any(Object),
            disbursalAmount: expect.any(Object),
            status: expect.any(Object),
            financerequest: expect.any(Object),
            financepartner: expect.any(Object),
          }),
        );
      });
    });

    describe('getPlacedOffer', () => {
      it('should return NewPlacedOffer for default PlacedOffer initial value', () => {
        const formGroup = service.createPlacedOfferFormGroup(sampleWithNewData);

        const placedOffer = service.getPlacedOffer(formGroup) as any;

        expect(placedOffer).toMatchObject(sampleWithNewData);
      });

      it('should return NewPlacedOffer for empty PlacedOffer initial value', () => {
        const formGroup = service.createPlacedOfferFormGroup();

        const placedOffer = service.getPlacedOffer(formGroup) as any;

        expect(placedOffer).toMatchObject({});
      });

      it('should return IPlacedOffer', () => {
        const formGroup = service.createPlacedOfferFormGroup(sampleWithRequiredData);

        const placedOffer = service.getPlacedOffer(formGroup) as any;

        expect(placedOffer).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPlacedOffer should not enable id FormControl', () => {
        const formGroup = service.createPlacedOfferFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPlacedOffer should disable id FormControl', () => {
        const formGroup = service.createPlacedOfferFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
