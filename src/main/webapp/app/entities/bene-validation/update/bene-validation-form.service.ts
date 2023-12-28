import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBeneValidation, NewBeneValidation } from '../bene-validation.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBeneValidation for edit and NewBeneValidationFormGroupInput for create.
 */
type BeneValidationFormGroupInput = IBeneValidation | PartialWithRequiredKeyOf<NewBeneValidation>;

type BeneValidationFormDefaults = Pick<NewBeneValidation, 'id'>;

type BeneValidationFormGroupContent = {
  id: FormControl<IBeneValidation['id'] | NewBeneValidation['id']>;
  benevalidationId: FormControl<IBeneValidation['benevalidationId']>;
  finReqId: FormControl<IBeneValidation['finReqId']>;
  subGrpId: FormControl<IBeneValidation['subGrpId']>;
  remitterName: FormControl<IBeneValidation['remitterName']>;
  remitterMobileNumber: FormControl<IBeneValidation['remitterMobileNumber']>;
  debtorAccountId: FormControl<IBeneValidation['debtorAccountId']>;
  accountType: FormControl<IBeneValidation['accountType']>;
  creditorAccountId: FormControl<IBeneValidation['creditorAccountId']>;
  ifscCode: FormControl<IBeneValidation['ifscCode']>;
  paymentDescription: FormControl<IBeneValidation['paymentDescription']>;
  transactionReferenceNumber: FormControl<IBeneValidation['transactionReferenceNumber']>;
  merchantCode: FormControl<IBeneValidation['merchantCode']>;
  identifier: FormControl<IBeneValidation['identifier']>;
  requestDateTime: FormControl<IBeneValidation['requestDateTime']>;
  metaDataStatus: FormControl<IBeneValidation['metaDataStatus']>;
  metaDataMessage: FormControl<IBeneValidation['metaDataMessage']>;
  metaDataVersion: FormControl<IBeneValidation['metaDataVersion']>;
  metaDataTime: FormControl<IBeneValidation['metaDataTime']>;
  resourceDataCreditorAccountId: FormControl<IBeneValidation['resourceDataCreditorAccountId']>;
  resourceDataCreditorName: FormControl<IBeneValidation['resourceDataCreditorName']>;
  resourceDataTransactionReferenceNumber: FormControl<IBeneValidation['resourceDataTransactionReferenceNumber']>;
  resourceDataTransactionId: FormControl<IBeneValidation['resourceDataTransactionId']>;
  resourceDataResponseCode: FormControl<IBeneValidation['resourceDataResponseCode']>;
  resourceDataTransactionTime: FormControl<IBeneValidation['resourceDataTransactionTime']>;
  resourceDataIdentifier: FormControl<IBeneValidation['resourceDataIdentifier']>;
  responseDateTime: FormControl<IBeneValidation['responseDateTime']>;
  lastupdatedDateTime: FormControl<IBeneValidation['lastupdatedDateTime']>;
  lastUpdatedBy: FormControl<IBeneValidation['lastUpdatedBy']>;
  tradeEntity: FormControl<IBeneValidation['tradeEntity']>;
};

export type BeneValidationFormGroup = FormGroup<BeneValidationFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BeneValidationFormService {
  createBeneValidationFormGroup(beneValidation: BeneValidationFormGroupInput = { id: null }): BeneValidationFormGroup {
    const beneValidationRawValue = {
      ...this.getFormDefaults(),
      ...beneValidation,
    };
    return new FormGroup<BeneValidationFormGroupContent>({
      id: new FormControl(
        { value: beneValidationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      benevalidationId: new FormControl(beneValidationRawValue.benevalidationId, {
        validators: [Validators.required],
      }),
      finReqId: new FormControl(beneValidationRawValue.finReqId),
      subGrpId: new FormControl(beneValidationRawValue.subGrpId),
      remitterName: new FormControl(beneValidationRawValue.remitterName),
      remitterMobileNumber: new FormControl(beneValidationRawValue.remitterMobileNumber),
      debtorAccountId: new FormControl(beneValidationRawValue.debtorAccountId),
      accountType: new FormControl(beneValidationRawValue.accountType),
      creditorAccountId: new FormControl(beneValidationRawValue.creditorAccountId),
      ifscCode: new FormControl(beneValidationRawValue.ifscCode),
      paymentDescription: new FormControl(beneValidationRawValue.paymentDescription),
      transactionReferenceNumber: new FormControl(beneValidationRawValue.transactionReferenceNumber),
      merchantCode: new FormControl(beneValidationRawValue.merchantCode),
      identifier: new FormControl(beneValidationRawValue.identifier),
      requestDateTime: new FormControl(beneValidationRawValue.requestDateTime, {
        validators: [Validators.required],
      }),
      metaDataStatus: new FormControl(beneValidationRawValue.metaDataStatus),
      metaDataMessage: new FormControl(beneValidationRawValue.metaDataMessage),
      metaDataVersion: new FormControl(beneValidationRawValue.metaDataVersion),
      metaDataTime: new FormControl(beneValidationRawValue.metaDataTime),
      resourceDataCreditorAccountId: new FormControl(beneValidationRawValue.resourceDataCreditorAccountId),
      resourceDataCreditorName: new FormControl(beneValidationRawValue.resourceDataCreditorName),
      resourceDataTransactionReferenceNumber: new FormControl(beneValidationRawValue.resourceDataTransactionReferenceNumber),
      resourceDataTransactionId: new FormControl(beneValidationRawValue.resourceDataTransactionId),
      resourceDataResponseCode: new FormControl(beneValidationRawValue.resourceDataResponseCode),
      resourceDataTransactionTime: new FormControl(beneValidationRawValue.resourceDataTransactionTime),
      resourceDataIdentifier: new FormControl(beneValidationRawValue.resourceDataIdentifier),
      responseDateTime: new FormControl(beneValidationRawValue.responseDateTime, {
        validators: [Validators.required],
      }),
      lastupdatedDateTime: new FormControl(beneValidationRawValue.lastupdatedDateTime, {
        validators: [Validators.required],
      }),
      lastUpdatedBy: new FormControl(beneValidationRawValue.lastUpdatedBy, {
        validators: [Validators.required],
      }),
      tradeEntity: new FormControl(beneValidationRawValue.tradeEntity),
    });
  }

  getBeneValidation(form: BeneValidationFormGroup): IBeneValidation | NewBeneValidation {
    return form.getRawValue() as IBeneValidation | NewBeneValidation;
  }

  resetForm(form: BeneValidationFormGroup, beneValidation: BeneValidationFormGroupInput): void {
    const beneValidationRawValue = { ...this.getFormDefaults(), ...beneValidation };
    form.reset(
      {
        ...beneValidationRawValue,
        id: { value: beneValidationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BeneValidationFormDefaults {
    return {
      id: null,
    };
  }
}
