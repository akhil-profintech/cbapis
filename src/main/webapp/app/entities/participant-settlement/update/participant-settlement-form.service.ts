import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IParticipantSettlement, NewParticipantSettlement } from '../participant-settlement.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IParticipantSettlement for edit and NewParticipantSettlementFormGroupInput for create.
 */
type ParticipantSettlementFormGroupInput = IParticipantSettlement | PartialWithRequiredKeyOf<NewParticipantSettlement>;

type ParticipantSettlementFormDefaults = Pick<NewParticipantSettlement, 'id'>;

type ParticipantSettlementFormGroupContent = {
  id: FormControl<IParticipantSettlement['id'] | NewParticipantSettlement['id']>;
  participantSettlementId: FormControl<IParticipantSettlement['participantSettlementId']>;
  participantSettlementUlidId: FormControl<IParticipantSettlement['participantSettlementUlidId']>;
  participantId: FormControl<IParticipantSettlement['participantId']>;
  participantName: FormControl<IParticipantSettlement['participantName']>;
  gstId: FormControl<IParticipantSettlement['gstId']>;
  settlementType: FormControl<IParticipantSettlement['settlementType']>;
  chargeType: FormControl<IParticipantSettlement['chargeType']>;
  settlementAmount: FormControl<IParticipantSettlement['settlementAmount']>;
  settlementDate: FormControl<IParticipantSettlement['settlementDate']>;
  settlementDueDate: FormControl<IParticipantSettlement['settlementDueDate']>;
  settlementContactInfo: FormControl<IParticipantSettlement['settlementContactInfo']>;
  patronOfPayment: FormControl<IParticipantSettlement['patronOfPayment']>;
  recipientOfPayment: FormControl<IParticipantSettlement['recipientOfPayment']>;
  settlementMethod: FormControl<IParticipantSettlement['settlementMethod']>;
  tenantId: FormControl<IParticipantSettlement['tenantId']>;
  accName: FormControl<IParticipantSettlement['accName']>;
  ifscCode: FormControl<IParticipantSettlement['ifscCode']>;
  accNumber: FormControl<IParticipantSettlement['accNumber']>;
  docId: FormControl<IParticipantSettlement['docId']>;
  recordStatus: FormControl<IParticipantSettlement['recordStatus']>;
  settlement: FormControl<IParticipantSettlement['settlement']>;
};

export type ParticipantSettlementFormGroup = FormGroup<ParticipantSettlementFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ParticipantSettlementFormService {
  createParticipantSettlementFormGroup(
    participantSettlement: ParticipantSettlementFormGroupInput = { id: null },
  ): ParticipantSettlementFormGroup {
    const participantSettlementRawValue = {
      ...this.getFormDefaults(),
      ...participantSettlement,
    };
    return new FormGroup<ParticipantSettlementFormGroupContent>({
      id: new FormControl(
        { value: participantSettlementRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      participantSettlementId: new FormControl(participantSettlementRawValue.participantSettlementId),
      participantSettlementUlidId: new FormControl(participantSettlementRawValue.participantSettlementUlidId),
      participantId: new FormControl(participantSettlementRawValue.participantId, {
        validators: [Validators.required],
      }),
      participantName: new FormControl(participantSettlementRawValue.participantName, {
        validators: [Validators.required],
      }),
      gstId: new FormControl(participantSettlementRawValue.gstId, {
        validators: [Validators.required],
      }),
      settlementType: new FormControl(participantSettlementRawValue.settlementType, {
        validators: [Validators.required],
      }),
      chargeType: new FormControl(participantSettlementRawValue.chargeType, {
        validators: [Validators.required],
      }),
      settlementAmount: new FormControl(participantSettlementRawValue.settlementAmount, {
        validators: [Validators.required],
      }),
      settlementDate: new FormControl(participantSettlementRawValue.settlementDate, {
        validators: [Validators.required],
      }),
      settlementDueDate: new FormControl(participantSettlementRawValue.settlementDueDate, {
        validators: [Validators.required],
      }),
      settlementContactInfo: new FormControl(participantSettlementRawValue.settlementContactInfo, {
        validators: [Validators.required],
      }),
      patronOfPayment: new FormControl(participantSettlementRawValue.patronOfPayment, {
        validators: [Validators.required],
      }),
      recipientOfPayment: new FormControl(participantSettlementRawValue.recipientOfPayment, {
        validators: [Validators.required],
      }),
      settlementMethod: new FormControl(participantSettlementRawValue.settlementMethod, {
        validators: [Validators.required],
      }),
      tenantId: new FormControl(participantSettlementRawValue.tenantId, {
        validators: [Validators.required],
      }),
      accName: new FormControl(participantSettlementRawValue.accName, {
        validators: [Validators.required],
      }),
      ifscCode: new FormControl(participantSettlementRawValue.ifscCode, {
        validators: [Validators.required],
      }),
      accNumber: new FormControl(participantSettlementRawValue.accNumber, {
        validators: [Validators.required],
      }),
      docId: new FormControl(participantSettlementRawValue.docId, {
        validators: [Validators.required],
      }),
      recordStatus: new FormControl(participantSettlementRawValue.recordStatus),
      settlement: new FormControl(participantSettlementRawValue.settlement),
    });
  }

  getParticipantSettlement(form: ParticipantSettlementFormGroup): IParticipantSettlement | NewParticipantSettlement {
    return form.getRawValue() as IParticipantSettlement | NewParticipantSettlement;
  }

  resetForm(form: ParticipantSettlementFormGroup, participantSettlement: ParticipantSettlementFormGroupInput): void {
    const participantSettlementRawValue = { ...this.getFormDefaults(), ...participantSettlement };
    form.reset(
      {
        ...participantSettlementRawValue,
        id: { value: participantSettlementRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ParticipantSettlementFormDefaults {
    return {
      id: null,
    };
  }
}
