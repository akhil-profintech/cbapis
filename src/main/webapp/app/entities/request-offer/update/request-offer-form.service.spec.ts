import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../request-offer.test-samples';

import { RequestOfferFormService } from './request-offer-form.service';

describe('RequestOffer Form Service', () => {
  let service: RequestOfferFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RequestOfferFormService);
  });

  describe('Service methods', () => {
    describe('createRequestOfferFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRequestOfferFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reqOffId: expect.any(Object),
            requestOfferRefNo: expect.any(Object),
            offerValue: expect.any(Object),
            requestAmt: expect.any(Object),
            tradeValue: expect.any(Object),
            marginPtg: expect.any(Object),
            marginValue: expect.any(Object),
            amountAftMargin: expect.any(Object),
            interestPtg: expect.any(Object),
            term: expect.any(Object),
            interestValue: expect.any(Object),
            netAmount: expect.any(Object),
            status: expect.any(Object),
            financeRequestDate: expect.any(Object),
            anchorTraderName: expect.any(Object),
            tradePartnerName: expect.any(Object),
            anchorTraderGst: expect.any(Object),
            tradePartnerGst: expect.any(Object),
            sellerName: expect.any(Object),
            buyerName: expect.any(Object),
            anchorTraderGstComplianceScore: expect.any(Object),
            anchorTraderErpPeerScore: expect.any(Object),
            financerequest: expect.any(Object),
            cbcreprocess: expect.any(Object),
          }),
        );
      });

      it('passing IRequestOffer should create a new form with FormGroup', () => {
        const formGroup = service.createRequestOfferFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reqOffId: expect.any(Object),
            requestOfferRefNo: expect.any(Object),
            offerValue: expect.any(Object),
            requestAmt: expect.any(Object),
            tradeValue: expect.any(Object),
            marginPtg: expect.any(Object),
            marginValue: expect.any(Object),
            amountAftMargin: expect.any(Object),
            interestPtg: expect.any(Object),
            term: expect.any(Object),
            interestValue: expect.any(Object),
            netAmount: expect.any(Object),
            status: expect.any(Object),
            financeRequestDate: expect.any(Object),
            anchorTraderName: expect.any(Object),
            tradePartnerName: expect.any(Object),
            anchorTraderGst: expect.any(Object),
            tradePartnerGst: expect.any(Object),
            sellerName: expect.any(Object),
            buyerName: expect.any(Object),
            anchorTraderGstComplianceScore: expect.any(Object),
            anchorTraderErpPeerScore: expect.any(Object),
            financerequest: expect.any(Object),
            cbcreprocess: expect.any(Object),
          }),
        );
      });
    });

    describe('getRequestOffer', () => {
      it('should return NewRequestOffer for default RequestOffer initial value', () => {
        const formGroup = service.createRequestOfferFormGroup(sampleWithNewData);

        const requestOffer = service.getRequestOffer(formGroup) as any;

        expect(requestOffer).toMatchObject(sampleWithNewData);
      });

      it('should return NewRequestOffer for empty RequestOffer initial value', () => {
        const formGroup = service.createRequestOfferFormGroup();

        const requestOffer = service.getRequestOffer(formGroup) as any;

        expect(requestOffer).toMatchObject({});
      });

      it('should return IRequestOffer', () => {
        const formGroup = service.createRequestOfferFormGroup(sampleWithRequiredData);

        const requestOffer = service.getRequestOffer(formGroup) as any;

        expect(requestOffer).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRequestOffer should not enable id FormControl', () => {
        const formGroup = service.createRequestOfferFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRequestOffer should disable id FormControl', () => {
        const formGroup = service.createRequestOfferFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
