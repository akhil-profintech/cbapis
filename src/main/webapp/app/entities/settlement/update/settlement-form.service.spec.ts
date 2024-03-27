import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../settlement.test-samples';

import { SettlementFormService } from './settlement-form.service';

describe('Settlement Form Service', () => {
  let service: SettlementFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SettlementFormService);
  });

  describe('Service methods', () => {
    describe('createSettlementFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSettlementFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            settlementId: expect.any(Object),
            settlementUlidId: expect.any(Object),
            settlementRefNo: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            placedOfferUlidId: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            dbmtRequestId: expect.any(Object),
            dbmtId: expect.any(Object),
            dbmtDate: expect.any(Object),
            dbmtStatus: expect.any(Object),
            dbmtAmount: expect.any(Object),
            currency: expect.any(Object),
            recordStatus: expect.any(Object),
            financerequest: expect.any(Object),
          }),
        );
      });

      it('passing ISettlement should create a new form with FormGroup', () => {
        const formGroup = service.createSettlementFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            settlementId: expect.any(Object),
            settlementUlidId: expect.any(Object),
            settlementRefNo: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            placedOfferUlidId: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            dbmtRequestId: expect.any(Object),
            dbmtId: expect.any(Object),
            dbmtDate: expect.any(Object),
            dbmtStatus: expect.any(Object),
            dbmtAmount: expect.any(Object),
            currency: expect.any(Object),
            recordStatus: expect.any(Object),
            financerequest: expect.any(Object),
          }),
        );
      });
    });

    describe('getSettlement', () => {
      it('should return NewSettlement for default Settlement initial value', () => {
        const formGroup = service.createSettlementFormGroup(sampleWithNewData);

        const settlement = service.getSettlement(formGroup) as any;

        expect(settlement).toMatchObject(sampleWithNewData);
      });

      it('should return NewSettlement for empty Settlement initial value', () => {
        const formGroup = service.createSettlementFormGroup();

        const settlement = service.getSettlement(formGroup) as any;

        expect(settlement).toMatchObject({});
      });

      it('should return ISettlement', () => {
        const formGroup = service.createSettlementFormGroup(sampleWithRequiredData);

        const settlement = service.getSettlement(formGroup) as any;

        expect(settlement).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISettlement should not enable id FormControl', () => {
        const formGroup = service.createSettlementFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSettlement should disable id FormControl', () => {
        const formGroup = service.createSettlementFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
