import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../collection-transaction-details.test-samples';

import { CollectionTransactionDetailsFormService } from './collection-transaction-details-form.service';

describe('CollectionTransactionDetails Form Service', () => {
  let service: CollectionTransactionDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CollectionTransactionDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createCollectionTransactionDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCollectionTransactionDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            trnxDetailsid: expect.any(Object),
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

      it('passing ICollectionTransactionDetails should create a new form with FormGroup', () => {
        const formGroup = service.createCollectionTransactionDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            trnxDetailsid: expect.any(Object),
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

    describe('getCollectionTransactionDetails', () => {
      it('should return NewCollectionTransactionDetails for default CollectionTransactionDetails initial value', () => {
        const formGroup = service.createCollectionTransactionDetailsFormGroup(sampleWithNewData);

        const collectionTransactionDetails = service.getCollectionTransactionDetails(formGroup) as any;

        expect(collectionTransactionDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewCollectionTransactionDetails for empty CollectionTransactionDetails initial value', () => {
        const formGroup = service.createCollectionTransactionDetailsFormGroup();

        const collectionTransactionDetails = service.getCollectionTransactionDetails(formGroup) as any;

        expect(collectionTransactionDetails).toMatchObject({});
      });

      it('should return ICollectionTransactionDetails', () => {
        const formGroup = service.createCollectionTransactionDetailsFormGroup(sampleWithRequiredData);

        const collectionTransactionDetails = service.getCollectionTransactionDetails(formGroup) as any;

        expect(collectionTransactionDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICollectionTransactionDetails should not enable id FormControl', () => {
        const formGroup = service.createCollectionTransactionDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCollectionTransactionDetails should disable id FormControl', () => {
        const formGroup = service.createCollectionTransactionDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
