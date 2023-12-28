import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IRequestOffer, NewRequestOffer } from '../request-offer.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRequestOffer for edit and NewRequestOfferFormGroupInput for create.
 */
type RequestOfferFormGroupInput = IRequestOffer | PartialWithRequiredKeyOf<NewRequestOffer>;

type RequestOfferFormDefaults = Pick<NewRequestOffer, 'id'>;

type RequestOfferFormGroupContent = {
  id: FormControl<IRequestOffer['id'] | NewRequestOffer['id']>;
  reqOffId: FormControl<IRequestOffer['reqOffId']>;
  requestOfferRefNo: FormControl<IRequestOffer['requestOfferRefNo']>;
  offerValue: FormControl<IRequestOffer['offerValue']>;
  requestAmt: FormControl<IRequestOffer['requestAmt']>;
  tradeValue: FormControl<IRequestOffer['tradeValue']>;
  marginPtg: FormControl<IRequestOffer['marginPtg']>;
  marginValue: FormControl<IRequestOffer['marginValue']>;
  amountAftMargin: FormControl<IRequestOffer['amountAftMargin']>;
  interestPtg: FormControl<IRequestOffer['interestPtg']>;
  term: FormControl<IRequestOffer['term']>;
  interestValue: FormControl<IRequestOffer['interestValue']>;
  netAmount: FormControl<IRequestOffer['netAmount']>;
  status: FormControl<IRequestOffer['status']>;
  financeRequestDate: FormControl<IRequestOffer['financeRequestDate']>;
  anchorTraderName: FormControl<IRequestOffer['anchorTraderName']>;
  tradePartnerName: FormControl<IRequestOffer['tradePartnerName']>;
  anchorTraderGst: FormControl<IRequestOffer['anchorTraderGst']>;
  tradePartnerGst: FormControl<IRequestOffer['tradePartnerGst']>;
  sellerName: FormControl<IRequestOffer['sellerName']>;
  buyerName: FormControl<IRequestOffer['buyerName']>;
  anchorTraderGstComplianceScore: FormControl<IRequestOffer['anchorTraderGstComplianceScore']>;
  anchorTraderErpPeerScore: FormControl<IRequestOffer['anchorTraderErpPeerScore']>;
  financerequest: FormControl<IRequestOffer['financerequest']>;
  cbcreprocess: FormControl<IRequestOffer['cbcreprocess']>;
};

export type RequestOfferFormGroup = FormGroup<RequestOfferFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RequestOfferFormService {
  createRequestOfferFormGroup(requestOffer: RequestOfferFormGroupInput = { id: null }): RequestOfferFormGroup {
    const requestOfferRawValue = {
      ...this.getFormDefaults(),
      ...requestOffer,
    };
    return new FormGroup<RequestOfferFormGroupContent>({
      id: new FormControl(
        { value: requestOfferRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      reqOffId: new FormControl(requestOfferRawValue.reqOffId),
      requestOfferRefNo: new FormControl(requestOfferRawValue.requestOfferRefNo),
      offerValue: new FormControl(requestOfferRawValue.offerValue, {
        validators: [Validators.required],
      }),
      requestAmt: new FormControl(requestOfferRawValue.requestAmt, {
        validators: [Validators.required],
      }),
      tradeValue: new FormControl(requestOfferRawValue.tradeValue, {
        validators: [Validators.required],
      }),
      marginPtg: new FormControl(requestOfferRawValue.marginPtg, {
        validators: [Validators.required],
      }),
      marginValue: new FormControl(requestOfferRawValue.marginValue, {
        validators: [Validators.required],
      }),
      amountAftMargin: new FormControl(requestOfferRawValue.amountAftMargin, {
        validators: [Validators.required],
      }),
      interestPtg: new FormControl(requestOfferRawValue.interestPtg, {
        validators: [Validators.required],
      }),
      term: new FormControl(requestOfferRawValue.term, {
        validators: [Validators.required],
      }),
      interestValue: new FormControl(requestOfferRawValue.interestValue, {
        validators: [Validators.required],
      }),
      netAmount: new FormControl(requestOfferRawValue.netAmount, {
        validators: [Validators.required],
      }),
      status: new FormControl(requestOfferRawValue.status, {
        validators: [Validators.required],
      }),
      financeRequestDate: new FormControl(requestOfferRawValue.financeRequestDate, {
        validators: [Validators.required],
      }),
      anchorTraderName: new FormControl(requestOfferRawValue.anchorTraderName, {
        validators: [Validators.required],
      }),
      tradePartnerName: new FormControl(requestOfferRawValue.tradePartnerName, {
        validators: [Validators.required],
      }),
      anchorTraderGst: new FormControl(requestOfferRawValue.anchorTraderGst),
      tradePartnerGst: new FormControl(requestOfferRawValue.tradePartnerGst),
      sellerName: new FormControl(requestOfferRawValue.sellerName),
      buyerName: new FormControl(requestOfferRawValue.buyerName),
      anchorTraderGstComplianceScore: new FormControl(requestOfferRawValue.anchorTraderGstComplianceScore),
      anchorTraderErpPeerScore: new FormControl(requestOfferRawValue.anchorTraderErpPeerScore),
      financerequest: new FormControl(requestOfferRawValue.financerequest),
      cbcreprocess: new FormControl(requestOfferRawValue.cbcreprocess),
    });
  }

  getRequestOffer(form: RequestOfferFormGroup): IRequestOffer | NewRequestOffer {
    return form.getRawValue() as IRequestOffer | NewRequestOffer;
  }

  resetForm(form: RequestOfferFormGroup, requestOffer: RequestOfferFormGroupInput): void {
    const requestOfferRawValue = { ...this.getFormDefaults(), ...requestOffer };
    form.reset(
      {
        ...requestOfferRawValue,
        id: { value: requestOfferRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): RequestOfferFormDefaults {
    return {
      id: null,
    };
  }
}
