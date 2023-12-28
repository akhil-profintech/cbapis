import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../escrow-account-details.test-samples';

import { EscrowAccountDetailsFormService } from './escrow-account-details-form.service';

describe('EscrowAccountDetails Form Service', () => {
  let service: EscrowAccountDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EscrowAccountDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createEscrowAccountDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEscrowAccountDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            escrowDetailsId: expect.any(Object),
            tenantId: expect.any(Object),
            customerId: expect.any(Object),
            accName: expect.any(Object),
            ifscCode: expect.any(Object),
            virtualAccNumber: expect.any(Object),
            poolingAccNumber: expect.any(Object),
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });

      it('passing IEscrowAccountDetails should create a new form with FormGroup', () => {
        const formGroup = service.createEscrowAccountDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            escrowDetailsId: expect.any(Object),
            tenantId: expect.any(Object),
            customerId: expect.any(Object),
            accName: expect.any(Object),
            ifscCode: expect.any(Object),
            virtualAccNumber: expect.any(Object),
            poolingAccNumber: expect.any(Object),
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });
    });

    describe('getEscrowAccountDetails', () => {
      it('should return NewEscrowAccountDetails for default EscrowAccountDetails initial value', () => {
        const formGroup = service.createEscrowAccountDetailsFormGroup(sampleWithNewData);

        const escrowAccountDetails = service.getEscrowAccountDetails(formGroup) as any;

        expect(escrowAccountDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewEscrowAccountDetails for empty EscrowAccountDetails initial value', () => {
        const formGroup = service.createEscrowAccountDetailsFormGroup();

        const escrowAccountDetails = service.getEscrowAccountDetails(formGroup) as any;

        expect(escrowAccountDetails).toMatchObject({});
      });

      it('should return IEscrowAccountDetails', () => {
        const formGroup = service.createEscrowAccountDetailsFormGroup(sampleWithRequiredData);

        const escrowAccountDetails = service.getEscrowAccountDetails(formGroup) as any;

        expect(escrowAccountDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEscrowAccountDetails should not enable id FormControl', () => {
        const formGroup = service.createEscrowAccountDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEscrowAccountDetails should disable id FormControl', () => {
        const formGroup = service.createEscrowAccountDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
