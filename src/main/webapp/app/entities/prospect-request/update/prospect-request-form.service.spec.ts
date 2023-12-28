import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../prospect-request.test-samples';

import { ProspectRequestFormService } from './prospect-request-form.service';

describe('ProspectRequest Form Service', () => {
  let service: ProspectRequestFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProspectRequestFormService);
  });

  describe('Service methods', () => {
    describe('createProspectRequestFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createProspectRequestFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            prospectRequestId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            requestAmount: expect.any(Object),
            prospectRequestDate: expect.any(Object),
            currency: expect.any(Object),
            financerequest: expect.any(Object),
          }),
        );
      });

      it('passing IProspectRequest should create a new form with FormGroup', () => {
        const formGroup = service.createProspectRequestFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            prospectRequestId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            requestAmount: expect.any(Object),
            prospectRequestDate: expect.any(Object),
            currency: expect.any(Object),
            financerequest: expect.any(Object),
          }),
        );
      });
    });

    describe('getProspectRequest', () => {
      it('should return NewProspectRequest for default ProspectRequest initial value', () => {
        const formGroup = service.createProspectRequestFormGroup(sampleWithNewData);

        const prospectRequest = service.getProspectRequest(formGroup) as any;

        expect(prospectRequest).toMatchObject(sampleWithNewData);
      });

      it('should return NewProspectRequest for empty ProspectRequest initial value', () => {
        const formGroup = service.createProspectRequestFormGroup();

        const prospectRequest = service.getProspectRequest(formGroup) as any;

        expect(prospectRequest).toMatchObject({});
      });

      it('should return IProspectRequest', () => {
        const formGroup = service.createProspectRequestFormGroup(sampleWithRequiredData);

        const prospectRequest = service.getProspectRequest(formGroup) as any;

        expect(prospectRequest).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IProspectRequest should not enable id FormControl', () => {
        const formGroup = service.createProspectRequestFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewProspectRequest should disable id FormControl', () => {
        const formGroup = service.createProspectRequestFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
