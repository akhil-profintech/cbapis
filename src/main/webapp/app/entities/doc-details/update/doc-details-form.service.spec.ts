import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../doc-details.test-samples';

import { DocDetailsFormService } from './doc-details-form.service';

describe('DocDetails Form Service', () => {
  let service: DocDetailsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DocDetailsFormService);
  });

  describe('Service methods', () => {
    describe('createDocDetailsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDocDetailsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            docDetailsId: expect.any(Object),
            docDetailsUlidId: expect.any(Object),
            docRecordId: expect.any(Object),
            link: expect.any(Object),
            description: expect.any(Object),
            docType: expect.any(Object),
            status: expect.any(Object),
            financeRequest: expect.any(Object),
          }),
        );
      });

      it('passing IDocDetails should create a new form with FormGroup', () => {
        const formGroup = service.createDocDetailsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            docDetailsId: expect.any(Object),
            docDetailsUlidId: expect.any(Object),
            docRecordId: expect.any(Object),
            link: expect.any(Object),
            description: expect.any(Object),
            docType: expect.any(Object),
            status: expect.any(Object),
            financeRequest: expect.any(Object),
          }),
        );
      });
    });

    describe('getDocDetails', () => {
      it('should return NewDocDetails for default DocDetails initial value', () => {
        const formGroup = service.createDocDetailsFormGroup(sampleWithNewData);

        const docDetails = service.getDocDetails(formGroup) as any;

        expect(docDetails).toMatchObject(sampleWithNewData);
      });

      it('should return NewDocDetails for empty DocDetails initial value', () => {
        const formGroup = service.createDocDetailsFormGroup();

        const docDetails = service.getDocDetails(formGroup) as any;

        expect(docDetails).toMatchObject({});
      });

      it('should return IDocDetails', () => {
        const formGroup = service.createDocDetailsFormGroup(sampleWithRequiredData);

        const docDetails = service.getDocDetails(formGroup) as any;

        expect(docDetails).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDocDetails should not enable id FormControl', () => {
        const formGroup = service.createDocDetailsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDocDetails should disable id FormControl', () => {
        const formGroup = service.createDocDetailsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
