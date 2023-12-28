import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IVANumber, NewVANumber } from '../va-number.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVANumber for edit and NewVANumberFormGroupInput for create.
 */
type VANumberFormGroupInput = IVANumber | PartialWithRequiredKeyOf<NewVANumber>;

type VANumberFormDefaults = Pick<NewVANumber, 'id'>;

type VANumberFormGroupContent = {
  id: FormControl<IVANumber['id'] | NewVANumber['id']>;
  vaId: FormControl<IVANumber['vaId']>;
  poolingAccNumber: FormControl<IVANumber['poolingAccNumber']>;
  virtualAccNumber: FormControl<IVANumber['virtualAccNumber']>;
  vaStatus: FormControl<IVANumber['vaStatus']>;
  financeRequestId: FormControl<IVANumber['financeRequestId']>;
  subGroupId: FormControl<IVANumber['subGroupId']>;
  tradeEntity: FormControl<IVANumber['tradeEntity']>;
};

export type VANumberFormGroup = FormGroup<VANumberFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VANumberFormService {
  createVANumberFormGroup(vANumber: VANumberFormGroupInput = { id: null }): VANumberFormGroup {
    const vANumberRawValue = {
      ...this.getFormDefaults(),
      ...vANumber,
    };
    return new FormGroup<VANumberFormGroupContent>({
      id: new FormControl(
        { value: vANumberRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      vaId: new FormControl(vANumberRawValue.vaId, {
        validators: [Validators.required],
      }),
      poolingAccNumber: new FormControl(vANumberRawValue.poolingAccNumber, {
        validators: [Validators.required],
      }),
      virtualAccNumber: new FormControl(vANumberRawValue.virtualAccNumber, {
        validators: [Validators.required],
      }),
      vaStatus: new FormControl(vANumberRawValue.vaStatus, {
        validators: [Validators.required],
      }),
      financeRequestId: new FormControl(vANumberRawValue.financeRequestId),
      subGroupId: new FormControl(vANumberRawValue.subGroupId),
      tradeEntity: new FormControl(vANumberRawValue.tradeEntity),
    });
  }

  getVANumber(form: VANumberFormGroup): IVANumber | NewVANumber {
    return form.getRawValue() as IVANumber | NewVANumber;
  }

  resetForm(form: VANumberFormGroup, vANumber: VANumberFormGroupInput): void {
    const vANumberRawValue = { ...this.getFormDefaults(), ...vANumber };
    form.reset(
      {
        ...vANumberRawValue,
        id: { value: vANumberRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VANumberFormDefaults {
    return {
      id: null,
    };
  }
}
