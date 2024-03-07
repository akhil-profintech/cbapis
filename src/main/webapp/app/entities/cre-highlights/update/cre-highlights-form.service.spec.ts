import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../cre-highlights.test-samples';

import { CREHighlightsFormService } from './cre-highlights-form.service';

describe('CREHighlights Form Service', () => {
  let service: CREHighlightsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CREHighlightsFormService);
  });

  describe('Service methods', () => {
    describe('createCREHighlightsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCREHighlightsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            creHighlightsId: expect.any(Object),
            creHighlightsUlidId: expect.any(Object),
            creRequestId: expect.any(Object),
            highlights: expect.any(Object),
            individualassessment: expect.any(Object),
          }),
        );
      });

      it('passing ICREHighlights should create a new form with FormGroup', () => {
        const formGroup = service.createCREHighlightsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            creHighlightsId: expect.any(Object),
            creHighlightsUlidId: expect.any(Object),
            creRequestId: expect.any(Object),
            highlights: expect.any(Object),
            individualassessment: expect.any(Object),
          }),
        );
      });
    });

    describe('getCREHighlights', () => {
      it('should return NewCREHighlights for default CREHighlights initial value', () => {
        const formGroup = service.createCREHighlightsFormGroup(sampleWithNewData);

        const cREHighlights = service.getCREHighlights(formGroup) as any;

        expect(cREHighlights).toMatchObject(sampleWithNewData);
      });

      it('should return NewCREHighlights for empty CREHighlights initial value', () => {
        const formGroup = service.createCREHighlightsFormGroup();

        const cREHighlights = service.getCREHighlights(formGroup) as any;

        expect(cREHighlights).toMatchObject({});
      });

      it('should return ICREHighlights', () => {
        const formGroup = service.createCREHighlightsFormGroup(sampleWithRequiredData);

        const cREHighlights = service.getCREHighlights(formGroup) as any;

        expect(cREHighlights).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICREHighlights should not enable id FormControl', () => {
        const formGroup = service.createCREHighlightsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCREHighlights should disable id FormControl', () => {
        const formGroup = service.createCREHighlightsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
