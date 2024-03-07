import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../gstin.test-samples';

import { GstinFormService } from './gstin-form.service';

describe('Gstin Form Service', () => {
  let service: GstinFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GstinFormService);
  });

  describe('Service methods', () => {
    describe('createGstinFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createGstinFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            companyName: expect.any(Object),
            gstId: expect.any(Object),
            organization: expect.any(Object),
          }),
        );
      });

      it('passing IGstin should create a new form with FormGroup', () => {
        const formGroup = service.createGstinFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            companyName: expect.any(Object),
            gstId: expect.any(Object),
            organization: expect.any(Object),
          }),
        );
      });
    });

    describe('getGstin', () => {
      it('should return NewGstin for default Gstin initial value', () => {
        const formGroup = service.createGstinFormGroup(sampleWithNewData);

        const gstin = service.getGstin(formGroup) as any;

        expect(gstin).toMatchObject(sampleWithNewData);
      });

      it('should return NewGstin for empty Gstin initial value', () => {
        const formGroup = service.createGstinFormGroup();

        const gstin = service.getGstin(formGroup) as any;

        expect(gstin).toMatchObject({});
      });

      it('should return IGstin', () => {
        const formGroup = service.createGstinFormGroup(sampleWithRequiredData);

        const gstin = service.getGstin(formGroup) as any;

        expect(gstin).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IGstin should not enable id FormControl', () => {
        const formGroup = service.createGstinFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewGstin should disable id FormControl', () => {
        const formGroup = service.createGstinFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
