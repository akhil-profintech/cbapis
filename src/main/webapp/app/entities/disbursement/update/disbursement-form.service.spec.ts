import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../disbursement.test-samples';

import { DisbursementFormService } from './disbursement-form.service';

describe('Disbursement Form Service', () => {
  let service: DisbursementFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisbursementFormService);
  });

  describe('Service methods', () => {
    describe('createDisbursementFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDisbursementFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dbmtId: expect.any(Object),
            disbursementUlidId: expect.any(Object),
            disbursementRefNo: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            placedOfferUlidId: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            offerAcceptedDate: expect.any(Object),
            dbmtRequestId: expect.any(Object),
            dbmtReqAmount: expect.any(Object),
            currency: expect.any(Object),
            dbmtRequestDate: expect.any(Object),
            dbmtStatus: expect.any(Object),
            offerStatus: expect.any(Object),
            docId: expect.any(Object),
            dbmtDateTime: expect.any(Object),
            dbmtAmount: expect.any(Object),
            financeRequestId: expect.any(Object),
            amountToBeDisbursed: expect.any(Object),
            destinationAccountName: expect.any(Object),
            destinationAccountNumber: expect.any(Object),
            recordStatus: expect.any(Object),
            actionByDate: expect.any(Object),
            financerequest: expect.any(Object),
            financepartner: expect.any(Object),
          }),
        );
      });

      it('passing IDisbursement should create a new form with FormGroup', () => {
        const formGroup = service.createDisbursementFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dbmtId: expect.any(Object),
            disbursementUlidId: expect.any(Object),
            disbursementRefNo: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            placedOfferUlidId: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            offerAcceptedDate: expect.any(Object),
            dbmtRequestId: expect.any(Object),
            dbmtReqAmount: expect.any(Object),
            currency: expect.any(Object),
            dbmtRequestDate: expect.any(Object),
            dbmtStatus: expect.any(Object),
            offerStatus: expect.any(Object),
            docId: expect.any(Object),
            dbmtDateTime: expect.any(Object),
            dbmtAmount: expect.any(Object),
            financeRequestId: expect.any(Object),
            amountToBeDisbursed: expect.any(Object),
            destinationAccountName: expect.any(Object),
            destinationAccountNumber: expect.any(Object),
            recordStatus: expect.any(Object),
            actionByDate: expect.any(Object),
            financerequest: expect.any(Object),
            financepartner: expect.any(Object),
          }),
        );
      });
    });

    describe('getDisbursement', () => {
      it('should return NewDisbursement for default Disbursement initial value', () => {
        const formGroup = service.createDisbursementFormGroup(sampleWithNewData);

        const disbursement = service.getDisbursement(formGroup) as any;

        expect(disbursement).toMatchObject(sampleWithNewData);
      });

      it('should return NewDisbursement for empty Disbursement initial value', () => {
        const formGroup = service.createDisbursementFormGroup();

        const disbursement = service.getDisbursement(formGroup) as any;

        expect(disbursement).toMatchObject({});
      });

      it('should return IDisbursement', () => {
        const formGroup = service.createDisbursementFormGroup(sampleWithRequiredData);

        const disbursement = service.getDisbursement(formGroup) as any;

        expect(disbursement).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDisbursement should not enable id FormControl', () => {
        const formGroup = service.createDisbursementFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDisbursement should disable id FormControl', () => {
        const formGroup = service.createDisbursementFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
