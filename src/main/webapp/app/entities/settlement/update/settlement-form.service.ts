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
  settlementRefNo: FormControl<ISettlement['settlementRefNo']>;
  offerId: FormControl<ISettlement['offerId']>;
  dbmtRequestId: FormControl<ISettlement['dbmtRequestId']>;
  dbmtId: FormControl<ISettlement['dbmtId']>;
  dbmtDate: FormControl<ISettlement['dbmtDate']>;
  dbmtStatus: FormControl<ISettlement['dbmtStatus']>;
  dbmtAmount: FormControl<ISettlement['dbmtAmount']>;
  currency: FormControl<ISettlement['currency']>;
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
      settlementRefNo: new FormControl(settlementRawValue.settlementRefNo),
      offerId: new FormControl(settlementRawValue.offerId, {
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
