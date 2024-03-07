import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../credit-account-details.test-samples';

import { CreditAccountDetailsFormService } from './credit-account-details-form.service';

describe('CreditAccountDetails Form Service', () => {
  let service: CreditAccountDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditAccountDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createCreditAccountDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreditAccountDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            creditAccDetailsId: expect.any(Object),
            creditAccountDetailsUlidId: expect.any(Object),
            tenantId: expect.any(Object),
            customerId: expect.any(Object),
            accName: expect.any(Object),
            ifscCode: expect.any(Object),
            accNumber: expect.any(Object),
            destinationAccountName: expect.any(Object),
            destinationAccountNumber: expect.any(Object),
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });

      it('passing ICreditAccountDetails should create a new form with FormGroup', () => {
        const formGroup = service.createCreditAccountDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            creditAccDetailsId: expect.any(Object),
            creditAccountDetailsUlidId: expect.any(Object),
            tenantId: expect.any(Object),
            customerId: expect.any(Object),
            accName: expect.any(Object),
            ifscCode: expect.any(Object),
            accNumber: expect.any(Object),
            destinationAccountName: expect.any(Object),
            destinationAccountNumber: expect.any(Object),
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });
    });

    describe('getCreditAccountDetails', () => {
      it('should return NewCreditAccountDetails for default CreditAccountDetails initial value', () => {
        const formGroup = service.createCreditAccountDetailsFormGroup(sampleWithNewData);

        const creditAccountDetails = service.getCreditAccountDetails(formGroup) as any;

        expect(creditAccountDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreditAccountDetails for empty CreditAccountDetails initial value', () => {
        const formGroup = service.createCreditAccountDetailsFormGroup();

        const creditAccountDetails = service.getCreditAccountDetails(formGroup) as any;

        expect(creditAccountDetails).toMatchObject({});
      });

      it('should return ICreditAccountDetails', () => {
        const formGroup = service.createCreditAccountDetailsFormGroup(sampleWithRequiredData);

        const creditAccountDetails = service.getCreditAccountDetails(formGroup) as any;

        expect(creditAccountDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreditAccountDetails should not enable id FormControl', () => {
        const formGroup = service.createCreditAccountDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreditAccountDetails should disable id FormControl', () => {
        const formGroup = service.createCreditAccountDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
