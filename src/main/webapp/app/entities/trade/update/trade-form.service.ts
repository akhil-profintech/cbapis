import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITrade, NewTrade } from '../trade.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITrade for edit and NewTradeFormGroupInput for create.
 */
type TradeFormGroupInput = ITrade | PartialWithRequiredKeyOf<NewTrade>;

type TradeFormDefaults = Pick<NewTrade, 'id'>;

type TradeFormGroupContent = {
  id: FormControl<ITrade['id'] | NewTrade['id']>;
  tradeId: FormControl<ITrade['tradeId']>;
  tradeRefNumber: FormControl<ITrade['tradeRefNumber']>;
  sellerGstId: FormControl<ITrade['sellerGstId']>;
  buyerGstId: FormControl<ITrade['buyerGstId']>;
  tradeAmount: FormControl<ITrade['tradeAmount']>;
  invoiceDate: FormControl<ITrade['invoiceDate']>;
  invoiceNumber: FormControl<ITrade['invoiceNumber']>;
  tradeDocType: FormControl<ITrade['tradeDocType']>;
  tradeDocSource: FormControl<ITrade['tradeDocSource']>;
  tradeDocCredibility: FormControl<ITrade['tradeDocCredibility']>;
  tradeMilestoneStatus: FormControl<ITrade['tradeMilestoneStatus']>;
  tradeAdvancePayment: FormControl<ITrade['tradeAdvancePayment']>;
  anchorTraderName: FormControl<ITrade['anchorTraderName']>;
  tradePartnerName: FormControl<ITrade['tradePartnerName']>;
  invoiceTerm: FormControl<ITrade['invoiceTerm']>;
  acceptanceFromTradePartner: FormControl<ITrade['acceptanceFromTradePartner']>;
  reasonForFinance: FormControl<ITrade['reasonForFinance']>;
  tradePartnerSector: FormControl<ITrade['tradePartnerSector']>;
  tradePartnerLocation: FormControl<ITrade['tradePartnerLocation']>;
  tradePartnerGstComplianceScore: FormControl<ITrade['tradePartnerGstComplianceScore']>;
  financerequest: FormControl<ITrade['financerequest']>;
  tradepartner: FormControl<ITrade['tradepartner']>;
  anchortrader: FormControl<ITrade['anchortrader']>;
};

export type TradeFormGroup = FormGroup<TradeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TradeFormService {
  createTradeFormGroup(trade: TradeFormGroupInput = { id: null }): TradeFormGroup {
    const tradeRawValue = {
      ...this.getFormDefaults(),
      ...trade,
    };
    return new FormGroup<TradeFormGroupContent>({
      id: new FormControl(
        { value: tradeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tradeId: new FormControl(tradeRawValue.tradeId),
      tradeRefNumber: new FormControl(tradeRawValue.tradeRefNumber),
      sellerGstId: new FormControl(tradeRawValue.sellerGstId),
      buyerGstId: new FormControl(tradeRawValue.buyerGstId),
      tradeAmount: new FormControl(tradeRawValue.tradeAmount),
      invoiceDate: new FormControl(tradeRawValue.invoiceDate),
      invoiceNumber: new FormControl(tradeRawValue.invoiceNumber),
      tradeDocType: new FormControl(tradeRawValue.tradeDocType),
      tradeDocSource: new FormControl(tradeRawValue.tradeDocSource),
      tradeDocCredibility: new FormControl(tradeRawValue.tradeDocCredibility),
      tradeMilestoneStatus: new FormControl(tradeRawValue.tradeMilestoneStatus),
      tradeAdvancePayment: new FormControl(tradeRawValue.tradeAdvancePayment),
      anchorTraderName: new FormControl(tradeRawValue.anchorTraderName),
      tradePartnerName: new FormControl(tradeRawValue.tradePartnerName),
      invoiceTerm: new FormControl(tradeRawValue.invoiceTerm),
      acceptanceFromTradePartner: new FormControl(tradeRawValue.acceptanceFromTradePartner),
      reasonForFinance: new FormControl(tradeRawValue.reasonForFinance),
      tradePartnerSector: new FormControl(tradeRawValue.tradePartnerSector),
      tradePartnerLocation: new FormControl(tradeRawValue.tradePartnerLocation),
      tradePartnerGstComplianceScore: new FormControl(tradeRawValue.tradePartnerGstComplianceScore),
      financerequest: new FormControl(tradeRawValue.financerequest),
      tradepartner: new FormControl(tradeRawValue.tradepartner),
      anchortrader: new FormControl(tradeRawValue.anchortrader),
    });
  }

  getTrade(form: TradeFormGroup): ITrade | NewTrade {
    return form.getRawValue() as ITrade | NewTrade;
  }

  resetForm(form: TradeFormGroup, trade: TradeFormGroupInput): void {
    const tradeRawValue = { ...this.getFormDefaults(), ...trade };
    form.reset(
      {
        ...tradeRawValue,
        id: { value: tradeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): TradeFormDefaults {
    return {
      id: null,
    };
  }
}
