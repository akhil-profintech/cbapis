import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UpdateVADetailComponent } from './update-va-detail.component';

describe('UpdateVA Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateVADetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: UpdateVADetailComponent,
              resolve: { updateVA: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(UpdateVADetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load updateVA on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', UpdateVADetailComponent);

      // THEN
      expect(instance.updateVA).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
