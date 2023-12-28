import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../credit-bazaar-integrator.test-samples';

import { CreditBazaarIntegratorFormService } from './credit-bazaar-integrator-form.service';

describe('CreditBazaarIntegrator Form Service', () => {
  let service: CreditBazaarIntegratorFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditBazaarIntegratorFormService);
  });

  describe('Service methods', () => {
    describe('createCreditBazaarIntegratorFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreditBazaarIntegratorFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tenantId: expect.any(Object),
            integratorId: expect.any(Object),
            orgId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
          }),
        );
      });

      it('passing ICreditBazaarIntegrator should create a new form with FormGroup', () => {
        const formGroup = service.createCreditBazaarIntegratorFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tenantId: expect.any(Object),
            integratorId: expect.any(Object),
            orgId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
          }),
        );
      });
    });

    describe('getCreditBazaarIntegrator', () => {
      it('should return NewCreditBazaarIntegrator for default CreditBazaarIntegrator initial value', () => {
        const formGroup = service.createCreditBazaarIntegratorFormGroup(sampleWithNewData);

        const creditBazaarIntegrator = service.getCreditBazaarIntegrator(formGroup) as any;

        expect(creditBazaarIntegrator).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreditBazaarIntegrator for empty CreditBazaarIntegrator initial value', () => {
        const formGroup = service.createCreditBazaarIntegratorFormGroup();

        const creditBazaarIntegrator = service.getCreditBazaarIntegrator(formGroup) as any;

        expect(creditBazaarIntegrator).toMatchObject({});
      });

      it('should return ICreditBazaarIntegrator', () => {
        const formGroup = service.createCreditBazaarIntegratorFormGroup(sampleWithRequiredData);

        const creditBazaarIntegrator = service.getCreditBazaarIntegrator(formGroup) as any;

        expect(creditBazaarIntegrator).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreditBazaarIntegrator should not enable id FormControl', () => {
        const formGroup = service.createCreditBazaarIntegratorFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreditBazaarIntegrator should disable id FormControl', () => {
        const formGroup = service.createCreditBazaarIntegratorFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
