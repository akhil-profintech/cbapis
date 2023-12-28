import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../insta-alert.test-samples';

import { InstaAlertFormService } from './insta-alert-form.service';

describe('InstaAlert Form Service', () => {
  let service: InstaAlertFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InstaAlertFormService);
  });

  describe('Service methods', () => {
    describe('createInstaAlertFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createInstaAlertFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            instaAlertId: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
            customerCode: expect.any(Object),
            customerName: expect.any(Object),
            productCode: expect.any(Object),
            poolingAccountNumber: expect.any(Object),
            transactionType: expect.any(Object),
            batchAmt: expect.any(Object),
            batchAmtCcd: expect.any(Object),
            creditDate: expect.any(Object),
            vaNumber: expect.any(Object),
            utrNo: expect.any(Object),
            creditGenerationTime: expect.any(Object),
            remitterName: expect.any(Object),
            remitterAccountNumber: expect.any(Object),
            ifscCode: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });

      it('passing IInstaAlert should create a new form with FormGroup', () => {
        const formGroup = service.createInstaAlertFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            instaAlertId: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
            customerCode: expect.any(Object),
            customerName: expect.any(Object),
            productCode: expect.any(Object),
            poolingAccountNumber: expect.any(Object),
            transactionType: expect.any(Object),
            batchAmt: expect.any(Object),
            batchAmtCcd: expect.any(Object),
            creditDate: expect.any(Object),
            vaNumber: expect.any(Object),
            utrNo: expect.any(Object),
            creditGenerationTime: expect.any(Object),
            remitterName: expect.any(Object),
            remitterAccountNumber: expect.any(Object),
            ifscCode: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });
    });

    describe('getInstaAlert', () => {
      it('should return NewInstaAlert for default InstaAlert initial value', () => {
        const formGroup = service.createInstaAlertFormGroup(sampleWithNewData);

        const instaAlert = service.getInstaAlert(formGroup) as any;

        expect(instaAlert).toMatchObject(sampleWithNewData);
      });

      it('should return NewInstaAlert for empty InstaAlert initial value', () => {
        const formGroup = service.createInstaAlertFormGroup();

        const instaAlert = service.getInstaAlert(formGroup) as any;

        expect(instaAlert).toMatchObject({});
      });

      it('should return IInstaAlert', () => {
        const formGroup = service.createInstaAlertFormGroup(sampleWithRequiredData);

        const instaAlert = service.getInstaAlert(formGroup) as any;

        expect(instaAlert).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IInstaAlert should not enable id FormControl', () => {
        const formGroup = service.createInstaAlertFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewInstaAlert should disable id FormControl', () => {
        const formGroup = service.createInstaAlertFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
