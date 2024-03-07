import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../escrow-transaction-details.test-samples';

import { EscrowTransactionDetailsFormService } from './escrow-transaction-details-form.service';

describe('EscrowTransactionDetails Form Service', () => {
  let service: EscrowTransactionDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EscrowTransactionDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createEscrowTransactionDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEscrowTransactionDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            escrowTrnxDetailsId: expect.any(Object),
            escrowTrnxDetailsUlidId: expect.any(Object),
            customerCode: expect.any(Object),
            customerName: expect.any(Object),
            productCode: expect.any(Object),
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
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });

      it('passing IEscrowTransactionDetails should create a new form with FormGroup', () => {
        const formGroup = service.createEscrowTransactionDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            escrowTrnxDetailsId: expect.any(Object),
            escrowTrnxDetailsUlidId: expect.any(Object),
            customerCode: expect.any(Object),
            customerName: expect.any(Object),
            productCode: expect.any(Object),
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
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });
    });

    describe('getEscrowTransactionDetails', () => {
      it('should return NewEscrowTransactionDetails for default EscrowTransactionDetails initial value', () => {
        const formGroup = service.createEscrowTransactionDetailsFormGroup(sampleWithNewData);

        const escrowTransactionDetails = service.getEscrowTransactionDetails(formGroup) as any;

        expect(escrowTransactionDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewEscrowTransactionDetails for empty EscrowTransactionDetails initial value', () => {
        const formGroup = service.createEscrowTransactionDetailsFormGroup();

        const escrowTransactionDetails = service.getEscrowTransactionDetails(formGroup) as any;

        expect(escrowTransactionDetails).toMatchObject({});
      });

      it('should return IEscrowTransactionDetails', () => {
        const formGroup = service.createEscrowTransactionDetailsFormGroup(sampleWithRequiredData);

        const escrowTransactionDetails = service.getEscrowTransactionDetails(formGroup) as any;

        expect(escrowTransactionDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEscrowTransactionDetails should not enable id FormControl', () => {
        const formGroup = service.createEscrowTransactionDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEscrowTransactionDetails should disable id FormControl', () => {
        const formGroup = service.createEscrowTransactionDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
