import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../ft-transaction-details.test-samples';

import { FTTransactionDetailsFormService } from './ft-transaction-details-form.service';

describe('FTTransactionDetails Form Service', () => {
  let service: FTTransactionDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FTTransactionDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createFTTransactionDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFTTransactionDetailsFormGroup();

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

      it('passing IFTTransactionDetails should create a new form with FormGroup', () => {
        const formGroup = service.createFTTransactionDetailsFormGroup(sampleWithRequiredData);

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

    describe('getFTTransactionDetails', () => {
      it('should return NewFTTransactionDetails for default FTTransactionDetails initial value', () => {
        const formGroup = service.createFTTransactionDetailsFormGroup(sampleWithNewData);

        const fTTransactionDetails = service.getFTTransactionDetails(formGroup) as any;

        expect(fTTransactionDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewFTTransactionDetails for empty FTTransactionDetails initial value', () => {
        const formGroup = service.createFTTransactionDetailsFormGroup();

        const fTTransactionDetails = service.getFTTransactionDetails(formGroup) as any;

        expect(fTTransactionDetails).toMatchObject({});
      });

      it('should return IFTTransactionDetails', () => {
        const formGroup = service.createFTTransactionDetailsFormGroup(sampleWithRequiredData);

        const fTTransactionDetails = service.getFTTransactionDetails(formGroup) as any;

        expect(fTTransactionDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFTTransactionDetails should not enable id FormControl', () => {
        const formGroup = service.createFTTransactionDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFTTransactionDetails should disable id FormControl', () => {
        const formGroup = service.createFTTransactionDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
