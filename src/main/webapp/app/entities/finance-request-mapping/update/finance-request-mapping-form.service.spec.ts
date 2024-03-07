import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../finance-request-mapping.test-samples';

import { FinanceRequestMappingFormService } from './finance-request-mapping-form.service';

describe('FinanceRequestMapping Form Service', () => {
  let service: FinanceRequestMappingFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FinanceRequestMappingFormService);
  });

  describe('Service methods', () => {
    describe('createFinanceRequestMappingFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFinanceRequestMappingFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            financeRequestId: expect.any(Object),
            financeRequestMappingUlidId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            financePartnerId: expect.any(Object),
            anchorTraderTenantId: expect.any(Object),
            financePartnerTenantId: expect.any(Object),
          }),
        );
      });

      it('passing IFinanceRequestMapping should create a new form with FormGroup', () => {
        const formGroup = service.createFinanceRequestMappingFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            financeRequestId: expect.any(Object),
            financeRequestMappingUlidId: expect.any(Object),
            anchorTraderId: expect.any(Object),
            financePartnerId: expect.any(Object),
            anchorTraderTenantId: expect.any(Object),
            financePartnerTenantId: expect.any(Object),
          }),
        );
      });
    });

    describe('getFinanceRequestMapping', () => {
      it('should return NewFinanceRequestMapping for default FinanceRequestMapping initial value', () => {
        const formGroup = service.createFinanceRequestMappingFormGroup(sampleWithNewData);

        const financeRequestMapping = service.getFinanceRequestMapping(formGroup) as any;

        expect(financeRequestMapping).toMatchObject(sampleWithNewData);
      });

      it('should return NewFinanceRequestMapping for empty FinanceRequestMapping initial value', () => {
        const formGroup = service.createFinanceRequestMappingFormGroup();

        const financeRequestMapping = service.getFinanceRequestMapping(formGroup) as any;

        expect(financeRequestMapping).toMatchObject({});
      });

      it('should return IFinanceRequestMapping', () => {
        const formGroup = service.createFinanceRequestMappingFormGroup(sampleWithRequiredData);

        const financeRequestMapping = service.getFinanceRequestMapping(formGroup) as any;

        expect(financeRequestMapping).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFinanceRequestMapping should not enable id FormControl', () => {
        const formGroup = service.createFinanceRequestMappingFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFinanceRequestMapping should disable id FormControl', () => {
        const formGroup = service.createFinanceRequestMappingFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
