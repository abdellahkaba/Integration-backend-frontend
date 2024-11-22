# Étape 1 : Construire l'application Angular
FROM node:20 as build-stage

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --prod

# Étape 2 : Utiliser Nginx pour servir l'application
FROM nginx:alpine

# Copier le fichier de configuration Nginx personnalisé
COPY nginx.conf /etc/nginx/nginx.conf

# Copier les fichiers Angular générés (à partir du dossier "browser" dans "dist")
COPY --from=build-stage /app/dist/demo-ui/browser /usr/share/nginx/html

# Exposer le port 80 pour Nginx
EXPOSE 80
