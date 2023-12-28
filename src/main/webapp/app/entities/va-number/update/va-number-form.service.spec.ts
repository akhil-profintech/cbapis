import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../va-number.test-samples';

import { VANumberFormService } from './va-number-form.service';

describe('VANumber Form Service', () => {
  let service: VANumberFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VANumberFormService);
  });

  describe('Service methods', () => {
    describe('createVANumberFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVANumberFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vaId: expect.any(Object),
            poolingAccNumber: expect.any(Object),
            virtualAccNumber: expect.any(Object),
            vaStatus: expect.any(Object),
            financeRequestId: expect.any(Object),
            subGroupId: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });

      it('passing IVANumber should create a new form with FormGroup', () => {
        const formGroup = service.createVANumberFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            vaId: expect.any(Object),
            poolingAccNumber: expect.any(Object),
            virtualAccNumber: expect.any(Object),
            vaStatus: expect.any(Object),
            financeRequestId: expect.any(Object),
            subGroupId: expect.any(Object),
            tradeEntity: expect.any(Object),
          }),
        );
      });
    });

    describe('getVANumber', () => {
      it('should return NewVANumber for default VANumber initial value', () => {
        const formGroup = service.createVANumberFormGroup(sampleWithNewData);

        const vANumber = service.getVANumber(formGroup) as any;

        expect(vANumber).toMatchObject(sampleWithNewData);
      });

      it('should return NewVANumber for empty VANumber initial value', () => {
        const formGroup = service.createVANumberFormGroup();

        const vANumber = service.getVANumber(formGroup) as any;

        expect(vANumber).toMatchObject({});
      });

      it('should return IVANumber', () => {
        const formGroup = service.createVANumberFormGroup(sampleWithRequiredData);

        const vANumber = service.getVANumber(formGroup) as any;

        expect(vANumber).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVANumber should not enable id FormControl', () => {
        const formGroup = service.createVANumberFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVANumber should disable id FormControl', () => {
        const formGroup = service.createVANumberFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
