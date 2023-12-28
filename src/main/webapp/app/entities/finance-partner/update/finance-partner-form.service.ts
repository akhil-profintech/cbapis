import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFinancePartner, NewFinancePartner } from '../finance-partner.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFinancePartner for edit and NewFinancePartnerFormGroupInput for create.
 */
type FinancePartnerFormGroupInput = IFinancePartner | PartialWithRequiredKeyOf<NewFinancePartner>;

type FinancePartnerFormDefaults = Pick<NewFinancePartner, 'id'>;

type FinancePartnerFormGroupContent = {
  id: FormControl<IFinancePartner['id'] | NewFinancePartner['id']>;
  tenantId: FormControl<IFinancePartner['tenantId']>;
  fpId: FormControl<IFinancePartner['fpId']>;
  orgId: FormControl<IFinancePartner['orgId']>;
  customerName: FormControl<IFinancePartner['customerName']>;
  orgName: FormControl<IFinancePartner['orgName']>;
  gstId: FormControl<IFinancePartner['gstId']>;
  phoneNumber: FormControl<IFinancePartner['phoneNumber']>;
};

export type FinancePartnerFormGroup = FormGroup<FinancePartnerFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FinancePartnerFormService {
  createFinancePartnerFormGroup(financePartner: FinancePartnerFormGroupInput = { id: null }): FinancePartnerFormGroup {
    const financePartnerRawValue = {
      ...this.getFormDefaults(),
      ...financePartner,
    };
    return new FormGroup<FinancePartnerFormGroupContent>({
      id: new FormControl(
        { value: financePartnerRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tenantId: new FormControl(financePartnerRawValue.tenantId, {
        validators: [Validators.required],
      }),
      fpId: new FormControl(financePartnerRawValue.fpId, {
        validators: [Validators.required],
      }),
      orgId: new FormControl(financePartnerRawValue.orgId, {
        validators: [Validators.required],
      }),
      customerName: new FormControl(financePartnerRawValue.customerName, {
        validators: [Validators.required],
      }),
      orgName: new FormControl(financePartnerRawValue.orgName, {
        validators: [Validators.required],
      }),
      gstId: new FormControl(financePartnerRawValue.gstId, {
        validators: [Validators.required],
      }),
      phoneNumber: new FormControl(financePartnerRawValue.phoneNumber, {
        validators: [Validators.required],
      }),
    });
  }

  getFinancePartner(form: FinancePartnerFormGroup): IFinancePartner | NewFinancePartner {
    return form.getRawValue() as IFinancePartner | NewFinancePartner;
  }

  resetForm(form: FinancePartnerFormGroup, financePartner: FinancePartnerFormGroupInput): void {
    const financePartnerRawValue = { ...this.getFormDefaults(), ...financePartner };
    form.reset(
      {
        ...financePartnerRawValue,
        id: { value: financePartnerRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FinancePartnerFormDefaults {
    return {
      id: null,
    };
  }
}
