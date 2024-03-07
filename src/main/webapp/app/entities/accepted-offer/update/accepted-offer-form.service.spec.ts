import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../accepted-offer.test-samples';

import { AcceptedOfferFormService } from './accepted-offer-form.service';

describe('AcceptedOffer Form Service', () => {
  let service: AcceptedOfferFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AcceptedOfferFormService);
  });

  describe('Service methods', () => {
    describe('createAcceptedOfferFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAcceptedOfferFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            acceptedOfferRefNo: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            value: expect.any(Object),
            reqAmount: expect.any(Object),
            marginPtg: expect.any(Object),
            marginValue: expect.any(Object),
            amountAftMargin: expect.any(Object),
            interestPtg: expect.any(Object),
            term: expect.any(Object),
            interestValue: expect.any(Object),
            netAmount: expect.any(Object),
            status: expect.any(Object),
            offerDate: expect.any(Object),
            offerAcceptedDate: expect.any(Object),
            financerequest: expect.any(Object),
            anchortrader: expect.any(Object),
            financepartner: expect.any(Object),
          }),
        );
      });

      it('passing IAcceptedOffer should create a new form with FormGroup', () => {
        const formGroup = service.createAcceptedOfferFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            acceptedOfferRefNo: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            value: expect.any(Object),
            reqAmount: expect.any(Object),
            marginPtg: expect.any(Object),
            marginValue: expect.any(Object),
            amountAftMargin: expect.any(Object),
            interestPtg: expect.any(Object),
            term: expect.any(Object),
            interestValue: expect.any(Object),
            netAmount: expect.any(Object),
            status: expect.any(Object),
            offerDate: expect.any(Object),
            offerAcceptedDate: expect.any(Object),
            financerequest: expect.any(Object),
            anchortrader: expect.any(Object),
            financepartner: expect.any(Object),
          }),
        );
      });
    });

    describe('getAcceptedOffer', () => {
      it('should return NewAcceptedOffer for default AcceptedOffer initial value', () => {
        const formGroup = service.createAcceptedOfferFormGroup(sampleWithNewData);

        const acceptedOffer = service.getAcceptedOffer(formGroup) as any;

        expect(acceptedOffer).toMatchObject(sampleWithNewData);
      });

      it('should return NewAcceptedOffer for empty AcceptedOffer initial value', () => {
        const formGroup = service.createAcceptedOfferFormGroup();

        const acceptedOffer = service.getAcceptedOffer(formGroup) as any;

        expect(acceptedOffer).toMatchObject({});
      });

      it('should return IAcceptedOffer', () => {
        const formGroup = service.createAcceptedOfferFormGroup(sampleWithRequiredData);

        const acceptedOffer = service.getAcceptedOffer(formGroup) as any;

        expect(acceptedOffer).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAcceptedOffer should not enable id FormControl', () => {
        const formGroup = service.createAcceptedOfferFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAcceptedOffer should disable id FormControl', () => {
        const formGroup = service.createAcceptedOfferFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
