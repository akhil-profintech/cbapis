import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITenantDetails, NewTenantDetails } from '../tenant-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITenantDetails for edit and NewTenantDetailsFormGroupInput for create.
 */
type TenantDetailsFormGroupInput = ITenantDetails | PartialWithRequiredKeyOf<NewTenantDetails>;

type TenantDetailsFormDefaults = Pick<NewTenantDetails, 'id'>;

type TenantDetailsFormGroupContent = {
  id: FormControl<ITenantDetails['id'] | NewTenantDetails['id']>;
  tenantUlidId: FormControl<ITenantDetails['tenantUlidId']>;
  tenantSchema: FormControl<ITenantDetails['tenantSchema']>;
};

export type TenantDetailsFormGroup = FormGroup<TenantDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TenantDetailsFormService {
  createTenantDetailsFormGroup(tenantDetails: TenantDetailsFormGroupInput = { id: null }): TenantDetailsFormGroup {
    const tenantDetailsRawValue = {
      ...this.getFormDefaults(),
      ...tenantDetails,
    };
    return new FormGroup<TenantDetailsFormGroupContent>({
      id: new FormControl(
        { value: tenantDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tenantUlidId: new FormControl(tenantDetailsRawValue.tenantUlidId),
      tenantSchema: new FormControl(tenantDetailsRawValue.tenantSchema),
    });
  }

  getTenantDetails(form: TenantDetailsFormGroup): ITenantDetails | NewTenantDetails {
    return form.getRawValue() as ITenantDetails | NewTenantDetails;
  }

  resetForm(form: TenantDetailsFormGroup, tenantDetails: TenantDetailsFormGroupInput): void {
    const tenantDetailsRawValue = { ...this.getFormDefaults(), ...tenantDetails };
    form.reset(
      {
        ...tenantDetailsRawValue,
        id: { value: tenantDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): TenantDetailsFormDefaults {
    return {
      id: null,
    };
  }
}
