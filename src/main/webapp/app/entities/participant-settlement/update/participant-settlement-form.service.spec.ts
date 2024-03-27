import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../participant-settlement.test-samples';

import { ParticipantSettlementFormService } from './participant-settlement-form.service';

describe('ParticipantSettlement Form Service', () => {
  let service: ParticipantSettlementFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticipantSettlementFormService);
  });

  describe('Service methods', () => {
    describe('createParticipantSettlementFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createParticipantSettlementFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            participantSettlementId: expect.any(Object),
            participantSettlementUlidId: expect.any(Object),
            participantId: expect.any(Object),
            participantName: expect.any(Object),
            gstId: expect.any(Object),
            settlementType: expect.any(Object),
            chargeType: expect.any(Object),
            settlementAmount: expect.any(Object),
            settlementDate: expect.any(Object),
            settlementDueDate: expect.any(Object),
            settlementContactInfo: expect.any(Object),
            patronOfPayment: expect.any(Object),
            recipientOfPayment: expect.any(Object),
            settlementMethod: expect.any(Object),
            tenantId: expect.any(Object),
            accName: expect.any(Object),
            ifscCode: expect.any(Object),
            accNumber: expect.any(Object),
            docId: expect.any(Object),
            recordStatus: expect.any(Object),
            settlement: expect.any(Object),
          }),
        );
      });

      it('passing IParticipantSettlement should create a new form with FormGroup', () => {
        const formGroup = service.createParticipantSettlementFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            participantSettlementId: expect.any(Object),
            participantSettlementUlidId: expect.any(Object),
            participantId: expect.any(Object),
            participantName: expect.any(Object),
            gstId: expect.any(Object),
            settlementType: expect.any(Object),
            chargeType: expect.any(Object),
            settlementAmount: expect.any(Object),
            settlementDate: expect.any(Object),
            settlementDueDate: expect.any(Object),
            settlementContactInfo: expect.any(Object),
            patronOfPayment: expect.any(Object),
            recipientOfPayment: expect.any(Object),
            settlementMethod: expect.any(Object),
            tenantId: expect.any(Object),
            accName: expect.any(Object),
            ifscCode: expect.any(Object),
            accNumber: expect.any(Object),
            docId: expect.any(Object),
            recordStatus: expect.any(Object),
            settlement: expect.any(Object),
          }),
        );
      });
    });

    describe('getParticipantSettlement', () => {
      it('should return NewParticipantSettlement for default ParticipantSettlement initial value', () => {
        const formGroup = service.createParticipantSettlementFormGroup(sampleWithNewData);

        const participantSettlement = service.getParticipantSettlement(formGroup) as any;

        expect(participantSettlement).toMatchObject(sampleWithNewData);
      });

      it('should return NewParticipantSettlement for empty ParticipantSettlement initial value', () => {
        const formGroup = service.createParticipantSettlementFormGroup();

        const participantSettlement = service.getParticipantSettlement(formGroup) as any;

        expect(participantSettlement).toMatchObject({});
      });

      it('should return IParticipantSettlement', () => {
        const formGroup = service.createParticipantSettlementFormGroup(sampleWithRequiredData);

        const participantSettlement = service.getParticipantSettlement(formGroup) as any;

        expect(participantSettlement).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IParticipantSettlement should not enable id FormControl', () => {
        const formGroup = service.createParticipantSettlementFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewParticipantSettlement should disable id FormControl', () => {
        const formGroup = service.createParticipantSettlementFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
