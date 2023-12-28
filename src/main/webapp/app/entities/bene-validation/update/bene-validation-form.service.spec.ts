import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../bene-validation.test-samples';

import { BeneValidationFormService } from './bene-validation-form.service';

describe('BeneValidation Form Service', () => {
  let service: BeneValidationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BeneValidationFormService);
  });

  describe('Service methods', () => {
    describe('createBeneValidationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBeneValidationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            benevalidationId: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
            remitterName: expect.any(Object),
            remitterMobileNumber: expect.any(Object),
            debtorAccountId: expect.any(Object),
            accountType: expect.any(Object),
            creditorAccountId: expect.any(Object),
            ifscCode: expect.any(Object),
            paymentDescription: expect.any(Object),
            transactionReferenceNumber: expect.any(Object),
            merchantCode: expect.any(Object),
            identifier: expect.any(Object),
            requestDateTime: expect.any(Object),
            metaDataStatus: expect.any(Object),
            metaDataMessage: expect.any(Object),
            metaDataVersion: expect.any(Object),
            metaDataTime: expect.any(Object),
            resourceDataCreditorAccountId: expect.any(Object),
            resourceDataCreditorName: expect.any(Object),
            resourceDataTransactionReferenceNumber: expect.any(Object),
            resourceDataTransactionId: expect.any(Object),
            resourceDataResponseCode: expect.any(Object),
            resourceDataTransactionTime: expect.any(Object),
            resourceDataIdentifier: expect.any(Object),
            responseDateTime: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });

      it('passing IBeneValidation should create a new form with FormGroup', () => {
        const formGroup = service.createBeneValidationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            benevalidationId: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
            remitterName: expect.any(Object),
            remitterMobileNumber: expect.any(Object),
            debtorAccountId: expect.any(Object),
            accountType: expect.any(Object),
            creditorAccountId: expect.any(Object),
            ifscCode: expect.any(Object),
            paymentDescription: expect.any(Object),
            transactionReferenceNumber: expect.any(Object),
            merchantCode: expect.any(Object),
            identifier: expect.any(Object),
            requestDateTime: expect.any(Object),
            metaDataStatus: expect.any(Object),
            metaDataMessage: expect.any(Object),
            metaDataVersion: expect.any(Object),
            metaDataTime: expect.any(Object),
            resourceDataCreditorAccountId: expect.any(Object),
            resourceDataCreditorName: expect.any(Object),
            resourceDataTransactionReferenceNumber: expect.any(Object),
            resourceDataTransactionId: expect.any(Object),
            resourceDataResponseCode: expect.any(Object),
            resourceDataTransactionTime: expect.any(Object),
            resourceDataIdentifier: expect.any(Object),
            responseDateTime: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });
    });

    describe('getBeneValidation', () => {
      it('should return NewBeneValidation for default BeneValidation initial value', () => {
        const formGroup = service.createBeneValidationFormGroup(sampleWithNewData);

        const beneValidation = service.getBeneValidation(formGroup) as any;

        expect(beneValidation).toMatchObject(sampleWithNewData);
      });

      it('should return NewBeneValidation for empty BeneValidation initial value', () => {
        const formGroup = service.createBeneValidationFormGroup();

        const beneValidation = service.getBeneValidation(formGroup) as any;

        expect(beneValidation).toMatchObject({});
      });

      it('should return IBeneValidation', () => {
        const formGroup = service.createBeneValidationFormGroup(sampleWithRequiredData);

        const beneValidation = service.getBeneValidation(formGroup) as any;

        expect(beneValidation).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBeneValidation should not enable id FormControl', () => {
        const formGroup = service.createBeneValidationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBeneValidation should disable id FormControl', () => {
        const formGroup = service.createBeneValidationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
