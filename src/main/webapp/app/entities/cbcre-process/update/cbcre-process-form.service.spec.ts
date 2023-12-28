import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../cbcre-process.test-samples';

import { CBCREProcessFormService } from './cbcre-process-form.service';

describe('CBCREProcess Form Service', () => {
  let service: CBCREProcessFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CBCREProcessFormService);
  });

  describe('Service methods', () => {
    describe('createCBCREProcessFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCBCREProcessFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cbProcessId: expect.any(Object),
            financeRequestId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            anchortTraderGst: expect.any(Object),
            financeAmount: expect.any(Object),
            processDateTime: expect.any(Object),
            creProcessStatus: expect.any(Object),
            responseDateTime: expect.any(Object),
            creRequestId: expect.any(Object),
            cumilativetradescore: expect.any(Object),
            timestamp: expect.any(Object),
            totalAmountRequested: expect.any(Object),
            totalInvoiceAmount: expect.any(Object),
          }),
        );
      });

      it('passing ICBCREProcess should create a new form with FormGroup', () => {
        const formGroup = service.createCBCREProcessFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cbProcessId: expect.any(Object),
            financeRequestId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            anchortTraderGst: expect.any(Object),
            financeAmount: expect.any(Object),
            processDateTime: expect.any(Object),
            creProcessStatus: expect.any(Object),
            responseDateTime: expect.any(Object),
            creRequestId: expect.any(Object),
            cumilativetradescore: expect.any(Object),
            timestamp: expect.any(Object),
            totalAmountRequested: expect.any(Object),
            totalInvoiceAmount: expect.any(Object),
          }),
        );
      });
    });

    describe('getCBCREProcess', () => {
      it('should return NewCBCREProcess for default CBCREProcess initial value', () => {
        const formGroup = service.createCBCREProcessFormGroup(sampleWithNewData);

        const cBCREProcess = service.getCBCREProcess(formGroup) as any;

        expect(cBCREProcess).toMatchObject(sampleWithNewData);
      });

      it('should return NewCBCREProcess for empty CBCREProcess initial value', () => {
        const formGroup = service.createCBCREProcessFormGroup();

        const cBCREProcess = service.getCBCREProcess(formGroup) as any;

        expect(cBCREProcess).toMatchObject({});
      });

      it('should return ICBCREProcess', () => {
        const formGroup = service.createCBCREProcessFormGroup(sampleWithRequiredData);

        const cBCREProcess = service.getCBCREProcess(formGroup) as any;

        expect(cBCREProcess).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICBCREProcess should not enable id FormControl', () => {
        const formGroup = service.createCBCREProcessFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCBCREProcess should disable id FormControl', () => {
        const formGroup = service.createCBCREProcessFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
