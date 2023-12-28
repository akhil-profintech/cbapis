import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IInstaAlert, NewInstaAlert } from '../insta-alert.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IInstaAlert for edit and NewInstaAlertFormGroupInput for create.
 */
type InstaAlertFormGroupInput = IInstaAlert | PartialWithRequiredKeyOf<NewInstaAlert>;

type InstaAlertFormDefaults = Pick<NewInstaAlert, 'id'>;

type InstaAlertFormGroupContent = {
  id: FormControl<IInstaAlert['id'] | NewInstaAlert['id']>;
  instaAlertId: FormControl<IInstaAlert['instaAlertId']>;
  finReqId: FormControl<IInstaAlert['finReqId']>;
  subGrpId: FormControl<IInstaAlert['subGrpId']>;
  customerCode: FormControl<IInstaAlert['customerCode']>;
  customerName: FormControl<IInstaAlert['customerName']>;
  productCode: FormControl<IInstaAlert['productCode']>;
  poolingAccountNumber: FormControl<IInstaAlert['poolingAccountNumber']>;
  transactionType: FormControl<IInstaAlert['transactionType']>;
  batchAmt: FormControl<IInstaAlert['batchAmt']>;
  batchAmtCcd: FormControl<IInstaAlert['batchAmtCcd']>;
  creditDate: FormControl<IInstaAlert['creditDate']>;
  vaNumber: FormControl<IInstaAlert['vaNumber']>;
  utrNo: FormControl<IInstaAlert['utrNo']>;
  creditGenerationTime: FormControl<IInstaAlert['creditGenerationTime']>;
  remitterName: FormControl<IInstaAlert['remitterName']>;
  remitterAccountNumber: FormControl<IInstaAlert['remitterAccountNumber']>;
  ifscCode: FormControl<IInstaAlert['ifscCode']>;
  lastupdatedDateTime: FormControl<IInstaAlert['lastupdatedDateTime']>;
  lastUpdatedBy: FormControl<IInstaAlert['lastUpdatedBy']>;
  tradeEntity: FormControl<IInstaAlert['tradeEntity']>;
};

export type InstaAlertFormGroup = FormGroup<InstaAlertFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class InstaAlertFormService {
  createInstaAlertFormGroup(instaAlert: InstaAlertFormGroupInput = { id: null }): InstaAlertFormGroup {
    const instaAlertRawValue = {
      ...this.getFormDefaults(),
      ...instaAlert,
    };
    return new FormGroup<InstaAlertFormGroupContent>({
      id: new FormControl(
        { value: instaAlertRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      instaAlertId: new FormControl(instaAlertRawValue.instaAlertId, {
        validators: [Validators.required],
      }),
      finReqId: new FormControl(instaAlertRawValue.finReqId),
      subGrpId: new FormControl(instaAlertRawValue.subGrpId),
      customerCode: new FormControl(instaAlertRawValue.customerCode),
      customerName: new FormControl(instaAlertRawValue.customerName),
      productCode: new FormControl(instaAlertRawValue.productCode),
      poolingAccountNumber: new FormControl(instaAlertRawValue.poolingAccountNumber),
      transactionType: new FormControl(instaAlertRawValue.transactionType),
      batchAmt: new FormControl(instaAlertRawValue.batchAmt),
      batchAmtCcd: new FormControl(instaAlertRawValue.batchAmtCcd),
      creditDate: new FormControl(instaAlertRawValue.creditDate),
      vaNumber: new FormControl(instaAlertRawValue.vaNumber),
      utrNo: new FormControl(instaAlertRawValue.utrNo),
      creditGenerationTime: new FormControl(instaAlertRawValue.creditGenerationTime),
      remitterName: new FormControl(instaAlertRawValue.remitterName),
      remitterAccountNumber: new FormControl(instaAlertRawValue.remitterAccountNumber),
      ifscCode: new FormControl(instaAlertRawValue.ifscCode),
      lastupdatedDateTime: new FormControl(instaAlertRawValue.lastupdatedDateTime, {
        validators: [Validators.required],
      }),
      lastUpdatedBy: new FormControl(instaAlertRawValue.lastUpdatedBy, {
        validators: [Validators.required],
      }),
      tradeEntity: new FormControl(instaAlertRawValue.tradeEntity),
    });
  }

  getInstaAlert(form: InstaAlertFormGroup): IInstaAlert | NewInstaAlert {
    return form.getRawValue() as IInstaAlert | NewInstaAlert;
  }

  resetForm(form: InstaAlertFormGroup, instaAlert: InstaAlertFormGroupInput): void {
    const instaAlertRawValue = { ...this.getFormDefaults(), ...instaAlert };
    form.reset(
      {
        ...instaAlertRawValue,
        id: { value: instaAlertRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): InstaAlertFormDefaults {
    return {
      id: null,
    };
  }
}
