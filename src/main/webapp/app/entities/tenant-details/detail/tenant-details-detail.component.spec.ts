import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TenantDetailsDetailComponent } from './tenant-details-detail.component';

describe('TenantDetails Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TenantDetailsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: TenantDetailsDetailComponent,
              resolve: { tenantDetails: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TenantDetailsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load tenantDetails on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TenantDetailsDetailComponent);

      // THEN
      expect(instance.tenantDetails).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
