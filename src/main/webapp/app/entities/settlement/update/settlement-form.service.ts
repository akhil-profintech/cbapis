import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISettlement, NewSettlement } from '../settlement.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISettlement for edit and NewSettlementFormGroupInput for create.
 */
type SettlementFormGroupInput = ISettlement | PartialWithRequiredKeyOf<NewSettlement>;

type SettlementFormDefaults = Pick<NewSettlement, 'id'>;

type SettlementFormGroupContent = {
  id: FormControl<ISettlement['id'] | NewSettlement['id']>;
  settlementId: FormControl<ISettlement['settlementId']>;
  settlementUlidId: FormControl<ISettlement['settlementUlidId']>;
  settlementRefNo: FormControl<ISettlement['settlementRefNo']>;
  acceptedOfferUlidId: FormControl<ISettlement['acceptedOfferUlidId']>;
  placedOfferUlidId: FormControl<ISettlement['placedOfferUlidId']>;
  reqOffUlidId: FormControl<ISettlement['reqOffUlidId']>;
  dbmtRequestId: FormControl<ISettlement['dbmtRequestId']>;
  dbmtId: FormControl<ISettlement['dbmtId']>;
  dbmtDate: FormControl<ISettlement['dbmtDate']>;
  dbmtStatus: FormControl<ISettlement['dbmtStatus']>;
  dbmtAmount: FormControl<ISettlement['dbmtAmount']>;
  currency: FormControl<ISettlement['currency']>;
  recordStatus: FormControl<ISettlement['recordStatus']>;
  financerequest: FormControl<ISettlement['financerequest']>;
};

export type SettlementFormGroup = FormGroup<SettlementFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SettlementFormService {
  createSettlementFormGroup(settlement: SettlementFormGroupInput = { id: null }): SettlementFormGroup {
    const settlementRawValue = {
      ...this.getFormDefaults(),
      ...settlement,
    };
    return new FormGroup<SettlementFormGroupContent>({
      id: new FormControl(
        { value: settlementRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      settlementId: new FormControl(settlementRawValue.settlementId),
      settlementUlidId: new FormControl(settlementRawValue.settlementUlidId),
      settlementRefNo: new FormControl(settlementRawValue.settlementRefNo),
      acceptedOfferUlidId: new FormControl(settlementRawValue.acceptedOfferUlidId),
      placedOfferUlidId: new FormControl(settlementRawValue.placedOfferUlidId, {
        validators: [Validators.required],
      }),
      reqOffUlidId: new FormControl(settlementRawValue.reqOffUlidId, {
        validators: [Validators.required],
      }),
      dbmtRequestId: new FormControl(settlementRawValue.dbmtRequestId, {
        validators: [Validators.required],
      }),
      dbmtId: new FormControl(settlementRawValue.dbmtId, {
        validators: [Validators.required],
      }),
      dbmtDate: new FormControl(settlementRawValue.dbmtDate, {
        validators: [Validators.required],
      }),
      dbmtStatus: new FormControl(settlementRawValue.dbmtStatus, {
        validators: [Validators.required],
      }),
      dbmtAmount: new FormControl(settlementRawValue.dbmtAmount, {
        validators: [Validators.required],
      }),
      currency: new FormControl(settlementRawValue.currency, {
        validators: [Validators.required],
      }),
      recordStatus: new FormControl(settlementRawValue.recordStatus),
      financerequest: new FormControl(settlementRawValue.financerequest),
    });
  }

  getSettlement(form: SettlementFormGroup): ISettlement | NewSettlement {
    return form.getRawValue() as ISettlement | NewSettlement;
  }

  resetForm(form: SettlementFormGroup, settlement: SettlementFormGroupInput): void {
    const settlementRawValue = { ...this.getFormDefaults(), ...settlement };
    form.reset(
      {
        ...settlementRawValue,
        id: { value: settlementRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): SettlementFormDefaults {
    return {
      id: null,
    };
  }
}
