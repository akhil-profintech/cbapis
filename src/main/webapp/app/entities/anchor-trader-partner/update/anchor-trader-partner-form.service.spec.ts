import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../anchor-trader-partner.test-samples';

import { AnchorTraderPartnerFormService } from './anchor-trader-partner-form.service';

describe('AnchorTraderPartner Form Service', () => {
  let service: AnchorTraderPartnerFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnchorTraderPartnerFormService);
  });

  describe('Service methods', () => {
    describe('createAnchorTraderPartnerFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAnchorTraderPartnerFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            atpartnerId: expect.any(Object),
            atpartnerUlidId: expect.any(Object),
            pan: expect.any(Object),
            panStatus: expect.any(Object),
            name: expect.any(Object),
            aadhar: expect.any(Object),
            aadharOtp: expect.any(Object),
            aadharStatus: expect.any(Object),
            aadharName: expect.any(Object),
            aadharAddress: expect.any(Object),
            anchortrader: expect.any(Object),
          }),
        );
      });

      it('passing IAnchorTraderPartner should create a new form with FormGroup', () => {
        const formGroup = service.createAnchorTraderPartnerFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            atpartnerId: expect.any(Object),
            atpartnerUlidId: expect.any(Object),
            pan: expect.any(Object),
            panStatus: expect.any(Object),
            name: expect.any(Object),
            aadhar: expect.any(Object),
            aadharOtp: expect.any(Object),
            aadharStatus: expect.any(Object),
            aadharName: expect.any(Object),
            aadharAddress: expect.any(Object),
            anchortrader: expect.any(Object),
          }),
        );
      });
    });

    describe('getAnchorTraderPartner', () => {
      it('should return NewAnchorTraderPartner for default AnchorTraderPartner initial value', () => {
        const formGroup = service.createAnchorTraderPartnerFormGroup(sampleWithNewData);

        const anchorTraderPartner = service.getAnchorTraderPartner(formGroup) as any;

        expect(anchorTraderPartner).toMatchObject(sampleWithNewData);
      });

      it('should return NewAnchorTraderPartner for empty AnchorTraderPartner initial value', () => {
        const formGroup = service.createAnchorTraderPartnerFormGroup();

        const anchorTraderPartner = service.getAnchorTraderPartner(formGroup) as any;

        expect(anchorTraderPartner).toMatchObject({});
      });

      it('should return IAnchorTraderPartner', () => {
        const formGroup = service.createAnchorTraderPartnerFormGroup(sampleWithRequiredData);

        const anchorTraderPartner = service.getAnchorTraderPartner(formGroup) as any;

        expect(anchorTraderPartner).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAnchorTraderPartner should not enable id FormControl', () => {
        const formGroup = service.createAnchorTraderPartnerFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAnchorTraderPartner should disable id FormControl', () => {
        const formGroup = service.createAnchorTraderPartnerFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
