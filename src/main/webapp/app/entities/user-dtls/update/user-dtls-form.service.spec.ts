import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../user-dtls.test-samples';

import { UserDtlsFormService } from './user-dtls-form.service';

describe('UserDtls Form Service', () => {
  let service: UserDtlsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserDtlsFormService);
  });

  describe('Service methods', () => {
    describe('createUserDtlsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createUserDtlsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            userId: expect.any(Object),
            userName: expect.any(Object),
            tenantId: expect.any(Object),
            isAnchorTraderEnabled: expect.any(Object),
            anchorTraderId: expect.any(Object),
            isTradePartnerEnabled: expect.any(Object),
            tradePartnerId: expect.any(Object),
            isFinancePartnerEnabled: expect.any(Object),
            financePartnerId: expect.any(Object),
            defaultPersona: expect.any(Object),
            organization: expect.any(Object),
          }),
        );
      });

      it('passing IUserDtls should create a new form with FormGroup', () => {
        const formGroup = service.createUserDtlsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            userId: expect.any(Object),
            userName: expect.any(Object),
            tenantId: expect.any(Object),
            isAnchorTraderEnabled: expect.any(Object),
            anchorTraderId: expect.any(Object),
            isTradePartnerEnabled: expect.any(Object),
            tradePartnerId: expect.any(Object),
            isFinancePartnerEnabled: expect.any(Object),
            financePartnerId: expect.any(Object),
            defaultPersona: expect.any(Object),
            organization: expect.any(Object),
          }),
        );
      });
    });

    describe('getUserDtls', () => {
      it('should return NewUserDtls for default UserDtls initial value', () => {
        const formGroup = service.createUserDtlsFormGroup(sampleWithNewData);

        const userDtls = service.getUserDtls(formGroup) as any;

        expect(userDtls).toMatchObject(sampleWithNewData);
      });

      it('should return NewUserDtls for empty UserDtls initial value', () => {
        const formGroup = service.createUserDtlsFormGroup();

        const userDtls = service.getUserDtls(formGroup) as any;

        expect(userDtls).toMatchObject({});
      });

      it('should return IUserDtls', () => {
        const formGroup = service.createUserDtlsFormGroup(sampleWithRequiredData);

        const userDtls = service.getUserDtls(formGroup) as any;

        expect(userDtls).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IUserDtls should not enable id FormControl', () => {
        const formGroup = service.createUserDtlsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewUserDtls should disable id FormControl', () => {
        const formGroup = service.createUserDtlsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
