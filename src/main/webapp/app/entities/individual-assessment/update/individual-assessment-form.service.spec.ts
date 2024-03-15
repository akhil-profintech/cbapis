import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../individual-assessment.test-samples';

import { IndividualAssessmentFormService } from './individual-assessment-form.service';

describe('IndividualAssessment Form Service', () => {
  let service: IndividualAssessmentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IndividualAssessmentFormService);
  });

  describe('Service methods', () => {
    describe('createIndividualAssessmentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createIndividualAssessmentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            assessmentId: expect.any(Object),
            assessmentUlidId: expect.any(Object),
            creditScore: expect.any(Object),
            finalVerdict: expect.any(Object),
            creRequestId: expect.any(Object),
            timestamp: expect.any(Object),
            tradePartnerGST: expect.any(Object),
            tradePartnerId: expect.any(Object),
            invoiceAmount: expect.any(Object),
            invoiceId: expect.any(Object),
            baseScore: expect.any(Object),
            ctin: expect.any(Object),
            invDate: expect.any(Object),
            cbProcessId: expect.any(Object),
            grnPresent: expect.any(Object),
            einvoicePresent: expect.any(Object),
            ewayBillPresent: expect.any(Object),
            tradePartnerConfirmation: expect.any(Object),
            cbcreprocess: expect.any(Object),
          }),
        );
      });

      it('passing IIndividualAssessment should create a new form with FormGroup', () => {
        const formGroup = service.createIndividualAssessmentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            assessmentId: expect.any(Object),
            assessmentUlidId: expect.any(Object),
            creditScore: expect.any(Object),
            finalVerdict: expect.any(Object),
            creRequestId: expect.any(Object),
            timestamp: expect.any(Object),
            tradePartnerGST: expect.any(Object),
            tradePartnerId: expect.any(Object),
            invoiceAmount: expect.any(Object),
            invoiceId: expect.any(Object),
            baseScore: expect.any(Object),
            ctin: expect.any(Object),
            invDate: expect.any(Object),
            cbProcessId: expect.any(Object),
            grnPresent: expect.any(Object),
            einvoicePresent: expect.any(Object),
            ewayBillPresent: expect.any(Object),
            tradePartnerConfirmation: expect.any(Object),
            cbcreprocess: expect.any(Object),
          }),
        );
      });
    });

    describe('getIndividualAssessment', () => {
      it('should return NewIndividualAssessment for default IndividualAssessment initial value', () => {
        const formGroup = service.createIndividualAssessmentFormGroup(sampleWithNewData);

        const individualAssessment = service.getIndividualAssessment(formGroup) as any;

        expect(individualAssessment).toMatchObject(sampleWithNewData);
      });

      it('should return NewIndividualAssessment for empty IndividualAssessment initial value', () => {
        const formGroup = service.createIndividualAssessmentFormGroup();

        const individualAssessment = service.getIndividualAssessment(formGroup) as any;

        expect(individualAssessment).toMatchObject({});
      });

      it('should return IIndividualAssessment', () => {
        const formGroup = service.createIndividualAssessmentFormGroup(sampleWithRequiredData);

        const individualAssessment = service.getIndividualAssessment(formGroup) as any;

        expect(individualAssessment).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IIndividualAssessment should not enable id FormControl', () => {
        const formGroup = service.createIndividualAssessmentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewIndividualAssessment should disable id FormControl', () => {
        const formGroup = service.createIndividualAssessmentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
