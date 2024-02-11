import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITradeChannel, NewTradeChannel } from '../trade-channel.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITradeChannel for edit and NewTradeChannelFormGroupInput for create.
 */
type TradeChannelFormGroupInput = ITradeChannel | PartialWithRequiredKeyOf<NewTradeChannel>;

type TradeChannelFormDefaults = Pick<NewTradeChannel, 'id'>;

type TradeChannelFormGroupContent = {
  id: FormControl<ITradeChannel['id'] | NewTradeChannel['id']>;
  tradeChannelId: FormControl<ITradeChannel['tradeChannelId']>;
  anchorTraderId: FormControl<ITradeChannel['anchorTraderId']>;
  tradePartnerId: FormControl<ITradeChannel['tradePartnerId']>;
  financePartnerId: FormControl<ITradeChannel['financePartnerId']>;
  anchorTraderTenantId: FormControl<ITradeChannel['anchorTraderTenantId']>;
  tradePartnerTenantId: FormControl<ITradeChannel['tradePartnerTenantId']>;
  financePartnerTenantId: FormControl<ITradeChannel['financePartnerTenantId']>;
};

export type TradeChannelFormGroup = FormGroup<TradeChannelFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TradeChannelFormService {
  createTradeChannelFormGroup(tradeChannel: TradeChannelFormGroupInput = { id: null }): TradeChannelFormGroup {
    const tradeChannelRawValue = {
      ...this.getFormDefaults(),
      ...tradeChannel,
    };
    return new FormGroup<TradeChannelFormGroupContent>({
      id: new FormControl(
        { value: tradeChannelRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tradeChannelId: new FormControl(tradeChannelRawValue.tradeChannelId),
      anchorTraderId: new FormControl(tradeChannelRawValue.anchorTraderId),
      tradePartnerId: new FormControl(tradeChannelRawValue.tradePartnerId),
      financePartnerId: new FormControl(tradeChannelRawValue.financePartnerId),
      anchorTraderTenantId: new FormControl(tradeChannelRawValue.anchorTraderTenantId),
      tradePartnerTenantId: new FormControl(tradeChannelRawValue.tradePartnerTenantId),
      financePartnerTenantId: new FormControl(tradeChannelRawValue.financePartnerTenantId),
    });
  }

  getTradeChannel(form: TradeChannelFormGroup): ITradeChannel | NewTradeChannel {
    return form.getRawValue() as ITradeChannel | NewTradeChannel;
  }

  resetForm(form: TradeChannelFormGroup, tradeChannel: TradeChannelFormGroupInput): void {
    const tradeChannelRawValue = { ...this.getFormDefaults(), ...tradeChannel };
    form.reset(
      {
        ...tradeChannelRawValue,
        id: { value: tradeChannelRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): TradeChannelFormDefaults {
    return {
      id: null,
    };
  }
}
