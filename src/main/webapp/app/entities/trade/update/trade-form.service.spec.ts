import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../trade.test-samples';

import { TradeFormService } from './trade-form.service';

describe('Trade Form Service', () => {
  let service: TradeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TradeFormService);
  });

  describe('Service methods', () => {
    describe('createTradeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTradeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tradeUlidId: expect.any(Object),
            tradeRefNo: expect.any(Object),
            sellerGstId: expect.any(Object),
            buyerGstId: expect.any(Object),
            tradeAmount: expect.any(Object),
            invoiceDate: expect.any(Object),
            invoiceNumber: expect.any(Object),
            tradeDocType: expect.any(Object),
            tradeDocSource: expect.any(Object),
            tradeDocCredibility: expect.any(Object),
            tradeMilestoneStatus: expect.any(Object),
            tradeAdvancePayment: expect.any(Object),
            anchorTraderName: expect.any(Object),
            tradePartnerName: expect.any(Object),
            invoiceTerm: expect.any(Object),
            acceptanceFromTradePartner: expect.any(Object),
            reasonForFinance: expect.any(Object),
            tradePartnerSector: expect.any(Object),
            tradePartnerLocation: expect.any(Object),
            tradePartnerGstComplianceScore: expect.any(Object),
            financerequest: expect.any(Object),
            anchortrader: expect.any(Object),
            tradepartner: expect.any(Object),
          }),
        );
      });

      it('passing ITrade should create a new form with FormGroup', () => {
        const formGroup = service.createTradeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tradeUlidId: expect.any(Object),
            tradeRefNo: expect.any(Object),
            sellerGstId: expect.any(Object),
            buyerGstId: expect.any(Object),
            tradeAmount: expect.any(Object),
            invoiceDate: expect.any(Object),
            invoiceNumber: expect.any(Object),
            tradeDocType: expect.any(Object),
            tradeDocSource: expect.any(Object),
            tradeDocCredibility: expect.any(Object),
            tradeMilestoneStatus: expect.any(Object),
            tradeAdvancePayment: expect.any(Object),
            anchorTraderName: expect.any(Object),
            tradePartnerName: expect.any(Object),
            invoiceTerm: expect.any(Object),
            acceptanceFromTradePartner: expect.any(Object),
            reasonForFinance: expect.any(Object),
            tradePartnerSector: expect.any(Object),
            tradePartnerLocation: expect.any(Object),
            tradePartnerGstComplianceScore: expect.any(Object),
            financerequest: expect.any(Object),
            anchortrader: expect.any(Object),
            tradepartner: expect.any(Object),
          }),
        );
      });
    });

    describe('getTrade', () => {
      it('should return NewTrade for default Trade initial value', () => {
        const formGroup = service.createTradeFormGroup(sampleWithNewData);

        const trade = service.getTrade(formGroup) as any;

        expect(trade).toMatchObject(sampleWithNewData);
      });

      it('should return NewTrade for empty Trade initial value', () => {
        const formGroup = service.createTradeFormGroup();

        const trade = service.getTrade(formGroup) as any;

        expect(trade).toMatchObject({});
      });

      it('should return ITrade', () => {
        const formGroup = service.createTradeFormGroup(sampleWithRequiredData);

        const trade = service.getTrade(formGroup) as any;

        expect(trade).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITrade should not enable id FormControl', () => {
        const formGroup = service.createTradeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTrade should disable id FormControl', () => {
        const formGroup = service.createTradeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
