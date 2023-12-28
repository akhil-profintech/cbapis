import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../funds-transfer.test-samples';

import { FundsTransferFormService } from './funds-transfer-form.service';

describe('FundsTransfer Form Service', () => {
  let service: FundsTransferFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FundsTransferFormService);
  });

  describe('Service methods', () => {
    describe('createFundsTransferFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFundsTransferFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            fundsTransferId: expect.any(Object),
            fundsTransferRefNo: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
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
            messageType: expect.any(Object),
            transactionDateTime: expect.any(Object),
            transactionRefNo: expect.any(Object),
            trnxMetaDataStatus: expect.any(Object),
            trnxMetaDataMessage: expect.any(Object),
            trnxMetaDataVersion: expect.any(Object),
            trnxMetaDataTime: expect.any(Object),
            trnxResourceDataBeneficiaryName: expect.any(Object),
            trnxResourceDataTransactionId: expect.any(Object),
            trnxResourceDataStatus: expect.any(Object),
            fundsTransferStatus: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });

      it('passing IFundsTransfer should create a new form with FormGroup', () => {
        const formGroup = service.createFundsTransferFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            fundsTransferId: expect.any(Object),
            fundsTransferRefNo: expect.any(Object),
            finReqId: expect.any(Object),
            subGrpId: expect.any(Object),
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
            messageType: expect.any(Object),
            transactionDateTime: expect.any(Object),
            transactionRefNo: expect.any(Object),
            trnxMetaDataStatus: expect.any(Object),
            trnxMetaDataMessage: expect.any(Object),
            trnxMetaDataVersion: expect.any(Object),
            trnxMetaDataTime: expect.any(Object),
            trnxResourceDataBeneficiaryName: expect.any(Object),
            trnxResourceDataTransactionId: expect.any(Object),
            trnxResourceDataStatus: expect.any(Object),
            fundsTransferStatus: expect.any(Object),
            lastupdatedDateTime: expect.any(Object),
            lastUpdatedBy: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });
    });

    describe('getFundsTransfer', () => {
      it('should return NewFundsTransfer for default FundsTransfer initial value', () => {
        const formGroup = service.createFundsTransferFormGroup(sampleWithNewData);

        const fundsTransfer = service.getFundsTransfer(formGroup) as any;

        expect(fundsTransfer).toMatchObject(sampleWithNewData);
      });

      it('should return NewFundsTransfer for empty FundsTransfer initial value', () => {
        const formGroup = service.createFundsTransferFormGroup();

        const fundsTransfer = service.getFundsTransfer(formGroup) as any;

        expect(fundsTransfer).toMatchObject({});
      });

      it('should return IFundsTransfer', () => {
        const formGroup = service.createFundsTransferFormGroup(sampleWithRequiredData);

        const fundsTransfer = service.getFundsTransfer(formGroup) as any;

        expect(fundsTransfer).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFundsTransfer should not enable id FormControl', () => {
        const formGroup = service.createFundsTransferFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFundsTransfer should disable id FormControl', () => {
        const formGroup = service.createFundsTransferFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
