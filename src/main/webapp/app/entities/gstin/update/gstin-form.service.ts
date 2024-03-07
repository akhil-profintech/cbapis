import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IGstin, NewGstin } from '../gstin.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IGstin for edit and NewGstinFormGroupInput for create.
 */
type GstinFormGroupInput = IGstin | PartialWithRequiredKeyOf<NewGstin>;

type GstinFormDefaults = Pick<NewGstin, 'id'>;

type GstinFormGroupContent = {
  id: FormControl<IGstin['id'] | NewGstin['id']>;
  companyName: FormControl<IGstin['companyName']>;
  gstId: FormControl<IGstin['gstId']>;
  organization: FormControl<IGstin['organization']>;
};

export type GstinFormGroup = FormGroup<GstinFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class GstinFormService {
  createGstinFormGroup(gstin: GstinFormGroupInput = { id: null }): GstinFormGroup {
    const gstinRawValue = {
      ...this.getFormDefaults(),
      ...gstin,
    };
    return new FormGroup<GstinFormGroupContent>({
      id: new FormControl(
        { value: gstinRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      companyName: new FormControl(gstinRawValue.companyName, {
        validators: [Validators.required],
      }),
      gstId: new FormControl(gstinRawValue.gstId, {
        validators: [Validators.required],
      }),
      organization: new FormControl(gstinRawValue.organization),
    });
  }

  getGstin(form: GstinFormGroup): IGstin | NewGstin {
    return form.getRawValue() as IGstin | NewGstin;
  }

  resetForm(form: GstinFormGroup, gstin: GstinFormGroupInput): void {
    const gstinRawValue = { ...this.getFormDefaults(), ...gstin };
    form.reset(
      {
        ...gstinRawValue,
        id: { value: gstinRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): GstinFormDefaults {
    return {
      id: null,
    };
  }
}
