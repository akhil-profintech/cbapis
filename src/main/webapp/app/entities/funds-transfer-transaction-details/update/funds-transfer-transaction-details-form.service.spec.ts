import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../funds-transfer-transaction-details.test-samples';

import { FundsTransferTransactionDetailsFormService } from './funds-transfer-transaction-details-form.service';

describe('FundsTransferTransactionDetails Form Service', () => {
  let service: FundsTransferTransactionDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FundsTransferTransactionDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createFundsTransferTransactionDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFundsTransferTransactionDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ftTrnxDetailsId: expect.any(Object),
            ftTrnxDetailsUlid: expect.any(Object),
            transactionId: expect.any(Object),
            debitAccountNumber: expect.any(Object),
            creditAccountNumber: expect.any(Object),
            remitterName: expect.any(Object),
            amount: expect.any(Object),
            currency: expect.any(Object),
            transactionType: expect.any(Object),
            paymentDescription: expect.any(Object),
            beneficiaryIFSC: expect.any(Object),
            beneficiaryName: expect.any(Object),
            beneficiaryAddress: expect.any(Object),
            emailId: expect.any(Object),
            mobileNo: expect.any(Object),
            transactionRefNo: expect.any(Object),
            trnxResourceDataStatus: expect.any(Object),
            trnxMetaDataStatus: expect.any(Object),
            transactionDateTime: expect.any(Object),
            participantsettlement: expect.any(Object),
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });

      it('passing IFundsTransferTransactionDetails should create a new form with FormGroup', () => {
        const formGroup = service.createFundsTransferTransactionDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ftTrnxDetailsId: expect.any(Object),
            ftTrnxDetailsUlid: expect.any(Object),
            transactionId: expect.any(Object),
            debitAccountNumber: expect.any(Object),
            creditAccountNumber: expect.any(Object),
            remitterName: expect.any(Object),
            amount: expect.any(Object),
            currency: expect.any(Object),
            transactionType: expect.any(Object),
            paymentDescription: expect.any(Object),
            beneficiaryIFSC: expect.any(Object),
            beneficiaryName: expect.any(Object),
            beneficiaryAddress: expect.any(Object),
            emailId: expect.any(Object),
            mobileNo: expect.any(Object),
            transactionRefNo: expect.any(Object),
            trnxResourceDataStatus: expect.any(Object),
            trnxMetaDataStatus: expect.any(Object),
            transactionDateTime: expect.any(Object),
            participantsettlement: expect.any(Object),
            disbursement: expect.any(Object),
            repayment: expect.any(Object),
          }),
        );
      });
    });

    describe('getFundsTransferTransactionDetails', () => {
      it('should return NewFundsTransferTransactionDetails for default FundsTransferTransactionDetails initial value', () => {
        const formGroup = service.createFundsTransferTransactionDetailsFormGroup(sampleWithNewData);

        const fundsTransferTransactionDetails = service.getFundsTransferTransactionDetails(formGroup) as any;

        expect(fundsTransferTransactionDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewFundsTransferTransactionDetails for empty FundsTransferTransactionDetails initial value', () => {
        const formGroup = service.createFundsTransferTransactionDetailsFormGroup();

        const fundsTransferTransactionDetails = service.getFundsTransferTransactionDetails(formGroup) as any;

        expect(fundsTransferTransactionDetails).toMatchObject({});
      });

      it('should return IFundsTransferTransactionDetails', () => {
        const formGroup = service.createFundsTransferTransactionDetailsFormGroup(sampleWithRequiredData);

        const fundsTransferTransactionDetails = service.getFundsTransferTransactionDetails(formGroup) as any;

        expect(fundsTransferTransactionDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFundsTransferTransactionDetails should not enable id FormControl', () => {
        const formGroup = service.createFundsTransferTransactionDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFundsTransferTransactionDetails should disable id FormControl', () => {
        const formGroup = service.createFundsTransferTransactionDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
