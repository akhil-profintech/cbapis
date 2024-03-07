import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITradePartner, NewTradePartner } from '../trade-partner.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITradePartner for edit and NewTradePartnerFormGroupInput for create.
 */
type TradePartnerFormGroupInput = ITradePartner | PartialWithRequiredKeyOf<NewTradePartner>;

type TradePartnerFormDefaults = Pick<NewTradePartner, 'id'>;

type TradePartnerFormGroupContent = {
  id: FormControl<ITradePartner['id'] | NewTradePartner['id']>;
  tpId: FormControl<ITradePartner['tpId']>;
  tpUlidId: FormControl<ITradePartner['tpUlidId']>;
  orgId: FormControl<ITradePartner['orgId']>;
  tenantId: FormControl<ITradePartner['tenantId']>;
  customerName: FormControl<ITradePartner['customerName']>;
  orgName: FormControl<ITradePartner['orgName']>;
  gstId: FormControl<ITradePartner['gstId']>;
  phoneNumber: FormControl<ITradePartner['phoneNumber']>;
  tradePartnerName: FormControl<ITradePartner['tradePartnerName']>;
  location: FormControl<ITradePartner['location']>;
  tradePartnerGST: FormControl<ITradePartner['tradePartnerGST']>;
  tradePartnerSector: FormControl<ITradePartner['tradePartnerSector']>;
  acceptanceFromTradePartner: FormControl<ITradePartner['acceptanceFromTradePartner']>;
  tosDocument: FormControl<ITradePartner['tosDocument']>;
};

export type TradePartnerFormGroup = FormGroup<TradePartnerFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TradePartnerFormService {
  createTradePartnerFormGroup(tradePartner: TradePartnerFormGroupInput = { id: null }): TradePartnerFormGroup {
    const tradePartnerRawValue = {
      ...this.getFormDefaults(),
      ...tradePartner,
    };
    return new FormGroup<TradePartnerFormGroupContent>({
      id: new FormControl(
        { value: tradePartnerRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tpId: new FormControl(tradePartnerRawValue.tpId),
      tpUlidId: new FormControl(tradePartnerRawValue.tpUlidId),
      orgId: new FormControl(tradePartnerRawValue.orgId, {
        validators: [Validators.required],
      }),
      tenantId: new FormControl(tradePartnerRawValue.tenantId, {
        validators: [Validators.required],
      }),
      customerName: new FormControl(tradePartnerRawValue.customerName, {
        validators: [Validators.required],
      }),
      orgName: new FormControl(tradePartnerRawValue.orgName, {
        validators: [Validators.required],
      }),
      gstId: new FormControl(tradePartnerRawValue.gstId, {
        validators: [Validators.required],
      }),
      phoneNumber: new FormControl(tradePartnerRawValue.phoneNumber, {
        validators: [Validators.required],
      }),
      tradePartnerName: new FormControl(tradePartnerRawValue.tradePartnerName),
      location: new FormControl(tradePartnerRawValue.location),
      tradePartnerGST: new FormControl(tradePartnerRawValue.tradePartnerGST),
      tradePartnerSector: new FormControl(tradePartnerRawValue.tradePartnerSector),
      acceptanceFromTradePartner: new FormControl(tradePartnerRawValue.acceptanceFromTradePartner),
      tosDocument: new FormControl(tradePartnerRawValue.tosDocument),
    });
  }

  getTradePartner(form: TradePartnerFormGroup): ITradePartner | NewTradePartner {
    return form.getRawValue() as ITradePartner | NewTradePartner;
  }

  resetForm(form: TradePartnerFormGroup, tradePartner: TradePartnerFormGroupInput): void {
    const tradePartnerRawValue = { ...this.getFormDefaults(), ...tradePartner };
    form.reset(
      {
        ...tradePartnerRawValue,
        id: { value: tradePartnerRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): TradePartnerFormDefaults {
    return {
      id: null,
    };
  }
}
