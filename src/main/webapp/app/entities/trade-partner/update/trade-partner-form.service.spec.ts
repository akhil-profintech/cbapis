import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../trade-partner.test-samples';

import { TradePartnerFormService } from './trade-partner-form.service';

describe('TradePartner Form Service', () => {
  let service: TradePartnerFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TradePartnerFormService);
  });

  describe('Service methods', () => {
    describe('createTradePartnerFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTradePartnerFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tenantId: expect.any(Object),
            tpId: expect.any(Object),
            orgId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
            tradePartnerName: expect.any(Object),
            location: expect.any(Object),
            tradepartnerGST: expect.any(Object),
            tradePartnerSector: expect.any(Object),
            acceptanceFromTradePartner: expect.any(Object),
          }),
        );
      });

      it('passing ITradePartner should create a new form with FormGroup', () => {
        const formGroup = service.createTradePartnerFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tenantId: expect.any(Object),
            tpId: expect.any(Object),
            orgId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
            tradePartnerName: expect.any(Object),
            location: expect.any(Object),
            tradepartnerGST: expect.any(Object),
            tradePartnerSector: expect.any(Object),
            acceptanceFromTradePartner: expect.any(Object),
          }),
        );
      });
    });

    describe('getTradePartner', () => {
      it('should return NewTradePartner for default TradePartner initial value', () => {
        const formGroup = service.createTradePartnerFormGroup(sampleWithNewData);

        const tradePartner = service.getTradePartner(formGroup) as any;

        expect(tradePartner).toMatchObject(sampleWithNewData);
      });

      it('should return NewTradePartner for empty TradePartner initial value', () => {
        const formGroup = service.createTradePartnerFormGroup();

        const tradePartner = service.getTradePartner(formGroup) as any;

        expect(tradePartner).toMatchObject({});
      });

      it('should return ITradePartner', () => {
        const formGroup = service.createTradePartnerFormGroup(sampleWithRequiredData);

        const tradePartner = service.getTradePartner(formGroup) as any;

        expect(tradePartner).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITradePartner should not enable id FormControl', () => {
        const formGroup = service.createTradePartnerFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTradePartner should disable id FormControl', () => {
        const formGroup = service.createTradePartnerFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
