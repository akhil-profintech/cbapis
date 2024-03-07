import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../trade-channel.test-samples';

import { TradeChannelFormService } from './trade-channel-form.service';

describe('TradeChannel Form Service', () => {
  let service: TradeChannelFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TradeChannelFormService);
  });

  describe('Service methods', () => {
    describe('createTradeChannelFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTradeChannelFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tradeChannelUlidId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            tradePartnerId: expect.any(Object),
            financePartnerId: expect.any(Object),
            anchorTraderTenantId: expect.any(Object),
            tradePartnerTenantId: expect.any(Object),
            financePartnerTenantId: expect.any(Object),
          }),
        );
      });

      it('passing ITradeChannel should create a new form with FormGroup', () => {
        const formGroup = service.createTradeChannelFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tradeChannelUlidId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            tradePartnerId: expect.any(Object),
            financePartnerId: expect.any(Object),
            anchorTraderTenantId: expect.any(Object),
            tradePartnerTenantId: expect.any(Object),
            financePartnerTenantId: expect.any(Object),
          }),
        );
      });
    });

    describe('getTradeChannel', () => {
      it('should return NewTradeChannel for default TradeChannel initial value', () => {
        const formGroup = service.createTradeChannelFormGroup(sampleWithNewData);

        const tradeChannel = service.getTradeChannel(formGroup) as any;

        expect(tradeChannel).toMatchObject(sampleWithNewData);
      });

      it('should return NewTradeChannel for empty TradeChannel initial value', () => {
        const formGroup = service.createTradeChannelFormGroup();

        const tradeChannel = service.getTradeChannel(formGroup) as any;

        expect(tradeChannel).toMatchObject({});
      });

      it('should return ITradeChannel', () => {
        const formGroup = service.createTradeChannelFormGroup(sampleWithRequiredData);

        const tradeChannel = service.getTradeChannel(formGroup) as any;

        expect(tradeChannel).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITradeChannel should not enable id FormControl', () => {
        const formGroup = service.createTradeChannelFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTradeChannel should disable id FormControl', () => {
        const formGroup = service.createTradeChannelFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
