import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../finance-partner.test-samples';

import { FinancePartnerFormService } from './finance-partner-form.service';

describe('FinancePartner Form Service', () => {
  let service: FinancePartnerFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FinancePartnerFormService);
  });

  describe('Service methods', () => {
    describe('createFinancePartnerFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFinancePartnerFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            fpId: expect.any(Object),
            fpUlidId: expect.any(Object),
            tenantId: expect.any(Object),
            orgId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
            tosDocument: expect.any(Object),
          }),
        );
      });

      it('passing IFinancePartner should create a new form with FormGroup', () => {
        const formGroup = service.createFinancePartnerFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            fpId: expect.any(Object),
            fpUlidId: expect.any(Object),
            tenantId: expect.any(Object),
            orgId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
            tosDocument: expect.any(Object),
          }),
        );
      });
    });

    describe('getFinancePartner', () => {
      it('should return NewFinancePartner for default FinancePartner initial value', () => {
        const formGroup = service.createFinancePartnerFormGroup(sampleWithNewData);

        const financePartner = service.getFinancePartner(formGroup) as any;

        expect(financePartner).toMatchObject(sampleWithNewData);
      });

      it('should return NewFinancePartner for empty FinancePartner initial value', () => {
        const formGroup = service.createFinancePartnerFormGroup();

        const financePartner = service.getFinancePartner(formGroup) as any;

        expect(financePartner).toMatchObject({});
      });

      it('should return IFinancePartner', () => {
        const formGroup = service.createFinancePartnerFormGroup(sampleWithRequiredData);

        const financePartner = service.getFinancePartner(formGroup) as any;

        expect(financePartner).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFinancePartner should not enable id FormControl', () => {
        const formGroup = service.createFinancePartnerFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFinancePartner should disable id FormControl', () => {
        const formGroup = service.createFinancePartnerFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
