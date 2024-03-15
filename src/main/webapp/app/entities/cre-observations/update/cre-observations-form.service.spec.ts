import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../cre-observations.test-samples';

import { CREObservationsFormService } from './cre-observations-form.service';

describe('CREObservations Form Service', () => {
  let service: CREObservationsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CREObservationsFormService);
  });

  describe('Service methods', () => {
    describe('createCREObservationsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCREObservationsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            creObservationsId: expect.any(Object),
            creObservationsUlidId: expect.any(Object),
            creRequestId: expect.any(Object),
            observations: expect.any(Object),
            assessmentId: expect.any(Object),
            individualassessment: expect.any(Object),
          }),
        );
      });

      it('passing ICREObservations should create a new form with FormGroup', () => {
        const formGroup = service.createCREObservationsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            creObservationsId: expect.any(Object),
            creObservationsUlidId: expect.any(Object),
            creRequestId: expect.any(Object),
            observations: expect.any(Object),
            assessmentId: expect.any(Object),
            individualassessment: expect.any(Object),
          }),
        );
      });
    });

    describe('getCREObservations', () => {
      it('should return NewCREObservations for default CREObservations initial value', () => {
        const formGroup = service.createCREObservationsFormGroup(sampleWithNewData);

        const cREObservations = service.getCREObservations(formGroup) as any;

        expect(cREObservations).toMatchObject(sampleWithNewData);
      });

      it('should return NewCREObservations for empty CREObservations initial value', () => {
        const formGroup = service.createCREObservationsFormGroup();

        const cREObservations = service.getCREObservations(formGroup) as any;

        expect(cREObservations).toMatchObject({});
      });

      it('should return ICREObservations', () => {
        const formGroup = service.createCREObservationsFormGroup(sampleWithRequiredData);

        const cREObservations = service.getCREObservations(formGroup) as any;

        expect(cREObservations).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICREObservations should not enable id FormControl', () => {
        const formGroup = service.createCREObservationsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCREObservations should disable id FormControl', () => {
        const formGroup = service.createCREObservationsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
