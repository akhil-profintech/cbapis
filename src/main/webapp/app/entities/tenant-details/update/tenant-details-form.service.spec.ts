import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../tenant-details.test-samples';

import { TenantDetailsFormService } from './tenant-details-form.service';

describe('TenantDetails Form Service', () => {
  let service: TenantDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TenantDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createTenantDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTenantDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tenantId: expect.any(Object),
            tenantSchema: expect.any(Object),
          }),
        );
      });

      it('passing ITenantDetails should create a new form with FormGroup', () => {
        const formGroup = service.createTenantDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tenantId: expect.any(Object),
            tenantSchema: expect.any(Object),
          }),
        );
      });
    });

    describe('getTenantDetails', () => {
      it('should return NewTenantDetails for default TenantDetails initial value', () => {
        const formGroup = service.createTenantDetailsFormGroup(sampleWithNewData);

        const tenantDetails = service.getTenantDetails(formGroup) as any;

        expect(tenantDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewTenantDetails for empty TenantDetails initial value', () => {
        const formGroup = service.createTenantDetailsFormGroup();

        const tenantDetails = service.getTenantDetails(formGroup) as any;

        expect(tenantDetails).toMatchObject({});
      });

      it('should return ITenantDetails', () => {
        const formGroup = service.createTenantDetailsFormGroup(sampleWithRequiredData);

        const tenantDetails = service.getTenantDetails(formGroup) as any;

        expect(tenantDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITenantDetails should not enable id FormControl', () => {
        const formGroup = service.createTenantDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTenantDetails should disable id FormControl', () => {
        const formGroup = service.createTenantDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
