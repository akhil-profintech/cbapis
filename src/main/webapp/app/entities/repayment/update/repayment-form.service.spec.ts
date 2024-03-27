import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../repayment.test-samples';

import { RepaymentFormService } from './repayment-form.service';

describe('Repayment Form Service', () => {
  let service: RepaymentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RepaymentFormService);
  });

  describe('Service methods', () => {
    describe('createRepaymentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRepaymentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            repaymentId: expect.any(Object),
            repaymentUlidId: expect.any(Object),
            repaymentRefNo: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            placedOfferUlidId: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            offerAcceptedDate: expect.any(Object),
            dbmtRequestId: expect.any(Object),
            dbmtStatus: expect.any(Object),
            dbmtDateTime: expect.any(Object),
            dbmtId: expect.any(Object),
            dbmtAmount: expect.any(Object),
            currency: expect.any(Object),
            repaymentStatus: expect.any(Object),
            repaymentDate: expect.any(Object),
            repaymentAmount: expect.any(Object),
            financeRequestId: expect.any(Object),
            repaymentDueDate: expect.any(Object),
            totalRepaymentAmount: expect.any(Object),
            amountRepayed: expect.any(Object),
            amountToBePaid: expect.any(Object),
            sourceAccountName: expect.any(Object),
            sourceAccountNumber: expect.any(Object),
            ifscCode: expect.any(Object),
            recordStatus: expect.any(Object),
            referenceNumber: expect.any(Object),
            financerequest: expect.any(Object),
          }),
        );
      });

      it('passing IRepayment should create a new form with FormGroup', () => {
        const formGroup = service.createRepaymentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            repaymentId: expect.any(Object),
            repaymentUlidId: expect.any(Object),
            repaymentRefNo: expect.any(Object),
            acceptedOfferUlidId: expect.any(Object),
            placedOfferUlidId: expect.any(Object),
            reqOffUlidId: expect.any(Object),
            offerAcceptedDate: expect.any(Object),
            dbmtRequestId: expect.any(Object),
            dbmtStatus: expect.any(Object),
            dbmtDateTime: expect.any(Object),
            dbmtId: expect.any(Object),
            dbmtAmount: expect.any(Object),
            currency: expect.any(Object),
            repaymentStatus: expect.any(Object),
            repaymentDate: expect.any(Object),
            repaymentAmount: expect.any(Object),
            financeRequestId: expect.any(Object),
            repaymentDueDate: expect.any(Object),
            totalRepaymentAmount: expect.any(Object),
            amountRepayed: expect.any(Object),
            amountToBePaid: expect.any(Object),
            sourceAccountName: expect.any(Object),
            sourceAccountNumber: expect.any(Object),
            ifscCode: expect.any(Object),
            recordStatus: expect.any(Object),
            referenceNumber: expect.any(Object),
            financerequest: expect.any(Object),
          }),
        );
      });
    });

    describe('getRepayment', () => {
      it('should return NewRepayment for default Repayment initial value', () => {
        const formGroup = service.createRepaymentFormGroup(sampleWithNewData);

        const repayment = service.getRepayment(formGroup) as any;

        expect(repayment).toMatchObject(sampleWithNewData);
      });

      it('should return NewRepayment for empty Repayment initial value', () => {
        const formGroup = service.createRepaymentFormGroup();

        const repayment = service.getRepayment(formGroup) as any;

        expect(repayment).toMatchObject({});
      });

      it('should return IRepayment', () => {
        const formGroup = service.createRepaymentFormGroup(sampleWithRequiredData);

        const repayment = service.getRepayment(formGroup) as any;

        expect(repayment).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRepayment should not enable id FormControl', () => {
        const formGroup = service.createRepaymentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRepayment should disable id FormControl', () => {
        const formGroup = service.createRepaymentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
