import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../anchor-trader.test-samples';

import { AnchorTraderFormService } from './anchor-trader-form.service';

describe('AnchorTrader Form Service', () => {
  let service: AnchorTraderFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnchorTraderFormService);
  });

  describe('Service methods', () => {
    describe('createAnchorTraderFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAnchorTraderFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            atId: expect.any(Object),
            atUlidId: expect.any(Object),
            orgId: expect.any(Object),
            tenantId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
            anchorTraderName: expect.any(Object),
            location: expect.any(Object),
            anchorTraderGST: expect.any(Object),
            anchorTraderSector: expect.any(Object),
            anchorTraderPAN: expect.any(Object),
            kycCompleted: expect.any(Object),
            bankDetails: expect.any(Object),
            emailId: expect.any(Object),
            accountNumber: expect.any(Object),
            ifscCode: expect.any(Object),
            bankName: expect.any(Object),
            branchName: expect.any(Object),
            erpAccess: expect.any(Object),
            typeOfFirm: expect.any(Object),
            panStatus: expect.any(Object),
            tosDocument: expect.any(Object),
            acceptTerms: expect.any(Object),
            acceptDeclaration: expect.any(Object),
            gstRegistrationCertificateUploadStatus: expect.any(Object),
            gstRegistrationCertificateVerificationStatus: expect.any(Object),
            udhyamRegistrationcertificateUploadStatus: expect.any(Object),
            udhyamRegistrationcertificateVerificationStatus: expect.any(Object),
            kycDeclaration: expect.any(Object),
          }),
        );
      });

      it('passing IAnchorTrader should create a new form with FormGroup', () => {
        const formGroup = service.createAnchorTraderFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            atId: expect.any(Object),
            atUlidId: expect.any(Object),
            orgId: expect.any(Object),
            tenantId: expect.any(Object),
            customerName: expect.any(Object),
            orgName: expect.any(Object),
            gstId: expect.any(Object),
            phoneNumber: expect.any(Object),
            anchorTraderName: expect.any(Object),
            location: expect.any(Object),
            anchorTraderGST: expect.any(Object),
            anchorTraderSector: expect.any(Object),
            anchorTraderPAN: expect.any(Object),
            kycCompleted: expect.any(Object),
            bankDetails: expect.any(Object),
            emailId: expect.any(Object),
            accountNumber: expect.any(Object),
            ifscCode: expect.any(Object),
            bankName: expect.any(Object),
            branchName: expect.any(Object),
            erpAccess: expect.any(Object),
            typeOfFirm: expect.any(Object),
            panStatus: expect.any(Object),
            tosDocument: expect.any(Object),
            acceptTerms: expect.any(Object),
            acceptDeclaration: expect.any(Object),
            gstRegistrationCertificateUploadStatus: expect.any(Object),
            gstRegistrationCertificateVerificationStatus: expect.any(Object),
            udhyamRegistrationcertificateUploadStatus: expect.any(Object),
            udhyamRegistrationcertificateVerificationStatus: expect.any(Object),
            kycDeclaration: expect.any(Object),
          }),
        );
      });
    });

    describe('getAnchorTrader', () => {
      it('should return NewAnchorTrader for default AnchorTrader initial value', () => {
        const formGroup = service.createAnchorTraderFormGroup(sampleWithNewData);

        const anchorTrader = service.getAnchorTrader(formGroup) as any;

        expect(anchorTrader).toMatchObject(sampleWithNewData);
      });

      it('should return NewAnchorTrader for empty AnchorTrader initial value', () => {
        const formGroup = service.createAnchorTraderFormGroup();

        const anchorTrader = service.getAnchorTrader(formGroup) as any;

        expect(anchorTrader).toMatchObject({});
      });

      it('should return IAnchorTrader', () => {
        const formGroup = service.createAnchorTraderFormGroup(sampleWithRequiredData);

        const anchorTrader = service.getAnchorTrader(formGroup) as any;

        expect(anchorTrader).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAnchorTrader should not enable id FormControl', () => {
        const formGroup = service.createAnchorTraderFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAnchorTrader should disable id FormControl', () => {
        const formGroup = service.createAnchorTraderFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
